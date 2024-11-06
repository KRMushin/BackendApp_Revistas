import { Component, inject, OnInit } from '@angular/core';
import { UsuariosService } from '../../../service/Usuarios/usuarios-service.service';
import { Usuario } from '../../../interfaces/Usuarios/usuario';
import { jwtDecode } from 'jwt-decode';
import { CommonModule, NgIf } from '@angular/common';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';
import { utileriaToken } from '../../../service/utileria-token.service';
import { HttpResponse } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { usuarioActualizado } from '../../../interfaces/Usuarios/usuarioActualizado';

@Component({
  selector: 'app-perfil-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './perfil-usuario.component.html',
  styleUrl: './perfil-usuario.component.css'
})
export class PerfilUsuarioComponent implements OnInit{
  

  private usuarioService = inject(UsuariosService);
  public perfilUsuario: Usuario | undefined;
  public fotourl?: string;
  private fotoSeleccionada: File | null = null;


  constructor(private controladorAnuncios: ControladorAnunciosService, 
              private utileriaToken: utileriaToken
  ) {}

  ngOnInit() {
    const nombreUsuario = this.utileriaToken.obtenerNombreUsuario();
  
    if (nombreUsuario) {
        this.cargarDatosUsuario(nombreUsuario);
        this.cargarFoto(nombreUsuario);

    } else {
      console.error('No se pudo obtener el nombre de usuario del token');
    }
  }
  
  cargarDatosUsuario(nombreUsuario: string) {
      this.usuarioService.obtenerDatosUsuario(nombreUsuario).subscribe({
        next: (usuario: Usuario) => {
          this.perfilUsuario = usuario;  
        },
        error: (error) => {
          console.error('Error al obtener los datos del usuario:', error); 
        },
        complete: () => {
          console.log('Petición finalizada con éxito');
        }
      });
    
  }

  cargarFoto(nombreUsuario: string) {
    this.usuarioService.obtnerFotoPerfil(nombreUsuario).subscribe({
      next: (response: Blob) => {
        if (!response || response.size === 0) {
          console.log('No se encontró una foto de perfil para el usuario.');
          this.fotourl = 'images/defaultuser.jpg'; // Ruta de una imagen de respaldo
        } else {
          this.fotourl = URL.createObjectURL(response);
        }
      },
      error: (error) => {
        console.error('Error al obtener la foto de perfil del usuario:', error);
      },
    });  
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.fotoSeleccionada = input.files[0];
    }
  }


  agregarPreferencia(tipo: string, valor: string) {
    if (this.perfilUsuario) {
      this.perfilUsuario.preferenciasUsuario = this.perfilUsuario.preferenciasUsuario || [];
      
      this.perfilUsuario.preferenciasUsuario.push({
        idPreferencia: 0,
        nombreUsuario: this.perfilUsuario.nombreUsuario,
        tipoPreferencia: tipo,
        valorPreferencia: valor
      });
      console.log('Preferencia agregada:', {
        tipoPreferencia: tipo,
        valorPreferencia: valor
      });
    }
  }
  

  eliminarPreferencia(preferencia: { idPreferencia: number }) {
    if (this.perfilUsuario) {
      this.perfilUsuario.preferenciasUsuario = this.perfilUsuario.preferenciasUsuario?.filter(
        p => p.idPreferencia !== preferencia.idPreferencia
      );
    }
  }

  // Método para actualizar la foto de perfil
  actualizarFoto() {
    if (this.fotoSeleccionada && this.perfilUsuario) {
      const formData = new FormData();
      formData.append('foto', this.fotoSeleccionada);

      this.usuarioService.actualizarFotoPerfil(formData, this.perfilUsuario.nombreUsuario).subscribe({
        next: () => {
          if(this.perfilUsuario?.nombreUsuario){
            this.cargarFoto(this.perfilUsuario.nombreUsuario); 
          }
        },
        error: (error) => {
          console.error('Error al actualizar la foto de perfil:', error);
        }
      });
    } else {
      alert('Por favor, selecciona una foto antes de actualizar.');
    }
  }

  actualizarUsuario() {
    //mapeo de los arrays de preferencias para enviar al backend
    if (this.perfilUsuario) {
      // const preferencias = this.perfilUsuario.preferenciasUsuario
      //   ?.filter(pref => pref.tipoPreferencia === 'TEMA_PREFERENCIA')
      //   .map(pref => pref.valorPreferencia);
  
      // const hobbies = this.perfilUsuario.preferenciasUsuario
      //   ?.filter(pref => pref.tipoPreferencia === 'HOBBIE')
      //   .map(pref => pref.valorPreferencia);
  
      // const gustos = this.perfilUsuario.preferenciasUsuario
      //   ?.filter(pref => pref.tipoPreferencia === 'GUSTO')
      //   .map(pref => pref.valorPreferencia);
  
      const usuarioActualizado: usuarioActualizado = {
        nombreUsuario: this.perfilUsuario.nombreUsuario,
        nombrePila: this.perfilUsuario.nombrePila,
        descripcion: this.perfilUsuario.descripcion,
        // preferencias: preferencias || [],
        // hobbies: hobbies || [],
        // gustos: gustos || []
      };

      console.log('Usuario actualizado:', usuarioActualizado);
      this.usuarioService.actualizarDatosUsuario(usuarioActualizado).subscribe({
        next: () => {
          alert('Datos del usuario actualizados correctamente');
          
          this.cargarDatosUsuario(this.perfilUsuario!.nombreUsuario);
          this.cargarFoto(this.perfilUsuario!.nombreUsuario);
        },
        error: (error) => console.error('Error al actualizar el usuario:', error)
      });
    }
  }
  
  ngOnDestroy(): void {
      this.controladorAnuncios.recargarAnuncios();
  }
  }



