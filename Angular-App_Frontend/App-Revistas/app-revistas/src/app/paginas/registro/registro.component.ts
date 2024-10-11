import { Component, inject } from '@angular/core';
import { AccesoService } from '../../service/acceso.service';
import { Router } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Registro } from '../../interfaces/Registro';
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
        // Manejar los diferentes códigos de error
        if (error.status === 409) {
          alert('Error: El nombre de usuario ya existe.');
        } else if (error.status === 400) {
          alert('Error: Datos inválidos en el formulario.');
        } else if (error.status === 500) {
          alert('Error: Problema en el servidor.');
        } else {
          alert('Error desconocido.');
        }
      }

    });  
    
  }  
  

}
