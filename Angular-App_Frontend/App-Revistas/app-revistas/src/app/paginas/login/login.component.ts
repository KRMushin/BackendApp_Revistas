import { Component, inject, Inject } from '@angular/core';
import { AccesoService } from '../../service/acceso.service';
import { Router } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Login } from '../../interfaces/Login';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  private accesoService = inject(AccesoService);
  private router = inject(Router); // para redireccionar de una pg a otra
  public formBuild = inject(FormBuilder);

  public loginForm = this.formBuild.group({
    nombreUsuario: ['', Validators.required],
    password: ['', Validators.required],
  });

  iniciarSesion() {
    
    // validacion de campos completos
    if(this.loginForm.invalid){
      return;
    }  
    
    const objeto: Login = {
      nombreUsuario: this.loginForm.value.nombreUsuario!,
      password: this.loginForm.value.password!
    }
    
    this.accesoService.login(objeto).subscribe({
      next: (data) => {
        if (data.isSuccessful) {
          localStorage.setItem('token', data.token);
          this.router.navigate(['/inicio']);
        } else {
          alert("Las credenciales son incorrectas");
        }
      },
      error: (error) => {
        console.log(error.message);
      }
    });
    console.log('Datos del formulario:', objeto);
  }

}
/*
    
*/ 