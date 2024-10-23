import { Component, inject } from '@angular/core';
import { AccesoService } from '../../../service/acceso.service';
import { Router } from '@angular/router';
import { Registro } from '../../../interfaces/Registro';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RolUsuario } from '../../enum/RolUsuario';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {

  private accesoServiceRest = inject(AccesoService);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);
  
  public registroForm = this.formBuilder.group({
    nombreUsuario: ['', Validators.required],
    nombreCompleto: ['', Validators.required],
    password: ['', Validators.required],
    rol: ['', Validators.required]
  });
  
  registrarUsuario() {

    if(this.registroForm.invalid){
      return;
    }

    const registro: Registro = {
      nombreUsuario: this.registroForm.value.nombreUsuario!,
      nombreCompleto: this.registroForm.value.nombreCompleto!,
      password: this.registroForm.value.password!,
      rol: this.registroForm.value.rol as RolUsuario
    }    


    this.accesoServiceRest.registrarUsuario(registro).subscribe({
      
      next: (response) => {
        // Si el registro es exitoso, redirigir al login
        alert('Usuario registrado exitosamente');
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Error al registrar usuario:', error);
      }

    });  
    
  }  
  

}
