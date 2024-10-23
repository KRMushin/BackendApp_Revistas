import { Component, inject, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Login } from '../../../interfaces/Login';
import { jwtDecode } from 'jwt-decode';
import { AccesoService } from '../../../service/acceso.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  mostrarPassword: boolean = false;
  private accesoService = inject(AccesoService);
  private router = inject(Router); // para redireccionar de una pg a otra
  public formBuild = inject(FormBuilder);

  public loginForm = this.formBuild.group({
    nombreUsuario: ['', Validators.required],
    password: ['', Validators.required],
  });

  iniciarSesion() {
    
    if(this.loginForm.invalid){
      return;
    }  
    
    const objeto: Login = {
      nombreUsuario: this.loginForm.value.nombreUsuario!,
      password: this.loginForm.value.password!
    }
    
    this.accesoService.login(objeto).subscribe({
      next: (data) => {
        if (data.estaAutenticado) {
          localStorage.setItem('token', data.token);

          const tokenValor: any = jwtDecode(data.token);
      const rolUsuario = tokenValor.rol;

      console.log('Rol del usuario:', rolUsuario);
            if (rolUsuario === 'ADMINISTRADOR') {
              this.router.navigate(['/admin-control']);

            } else if (rolUsuario === 'EDITOR') {
              this.router.navigate(['/editor-control']);
            }
              else if (rolUsuario === 'COMPRADOR') {
              this.router.navigate(['/comprador-control']);
            } 
              else if (rolUsuario === 'SUSCRIPTOR') {
              this.router.navigate(['/suscriptor-control']);

            } else {
              this.router.navigate(['']);
            }
        } else {
          alert("Las credenciales son incorrectas");
        }
      },
      error: (error) => {
        console.log("datos invalidos");
      }
    });
  }
}
