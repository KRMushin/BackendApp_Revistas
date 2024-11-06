import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Registro } from '../../../../interfaces/Registro';
import { RolUsuario } from '../../../enum/RolUsuario';
import { AccesoService } from '../../../../service/acceso.service';

@Component({
  selector: 'app-agregar-administrador',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './agregar-administrador.component.html',
  styleUrl: './agregar-administrador.component.css'
})
export class AgregarAdministradorComponent {
  adminForm: FormGroup;
  private usuarios = inject(AccesoService);
  showPassword = false;


  constructor(private fb: FormBuilder) {
    this.adminForm = this.fb.group({
      nombreUsuario: ['', [Validators.required, Validators.maxLength(15)]],
      nombreCompleto: ['', Validators.required],
      rol: [{ value: RolUsuario.ADMINISTRADOR, disabled: true }],
      password: ['', [Validators.required, Validators.pattern(/^\S+$/)]]
    });
  }

  onSubmit() {
    if (this.adminForm.valid) {
      const formData: Registro = this.adminForm.getRawValue();
      console.log('Usuario agregado:', formData);
      this.usuarios.registrarAdministrador(formData).subscribe({
        next: (response) => {
          alert('Administrador registrado exitosamente');
          this.adminForm.reset({
            rol: 'ADMINISTRADOR'
          });
        },
        error: (error) => {
          console.error('Error al registrar usuario:', error);
        }
      });
    }else{
      alert('Formulario inválido');
    }
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
}
