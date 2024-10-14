import { Component, inject, OnInit } from '@angular/core';
import { UsuariosService } from '../../../service/Usuarios/usuarios-service.service';
import { Usuario } from '../../../interfaces/Usuarios/usuario';
import { jwtDecode } from 'jwt-decode';
import { CommonModule, NgIf } from '@angular/common';

@Component({
  selector: 'app-perfil-usuario',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './perfil-usuario.component.html',
  styleUrl: './perfil-usuario.component.css'
})
export class PerfilUsuarioComponent implements OnInit{

  private usuarioService = inject(UsuariosService);
  public perfilUsuario: Usuario | undefined;

  ngOnInit() {
    const token = localStorage.getItem('token');
    const nombreUsuario = this.obtenerNombreUsuario(token);
  
    if (nombreUsuario !== null) {
      // Hacer la petición para obtener los datos del usuario
      this.usuarioService.obtenerDatosUsuario(nombreUsuario).subscribe({
        next: (usuario: Usuario) => {
          this.perfilUsuario = usuario;  // Asignar los datos recibidos al perfil del usuario
        },
        error: (error) => {
          console.error('Error al obtener los datos del usuario:', error);  // Manejo del error
        },
        complete: () => {
          console.log('Petición finalizada con éxito');
        }
      });
    } else {
      console.error('No se pudo obtener el nombre de usuario del token');
    }
  }
    obtenerNombreUsuario(token: string | null): string | null {
      if (!token) return null;

      try {
        const tokenValor: any = jwtDecode(token);  // Decodificar el token
        const nombreUsuario = tokenValor.sub || null;  
        return nombreUsuario;
      } catch (e) {
        console.error('Error decoding token:', e);
        return null;
      }
    }
  }



