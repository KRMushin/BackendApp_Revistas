import { Component, inject, OnInit } from '@angular/core';
import { UsuariosService } from '../../../../service/Usuarios/usuarios-service.service';
import { Usuario } from '../../../../interfaces/Usuarios/usuario';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';

@Component({
  selector: 'app-ver-autor',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ver-autor.component.html',
  styleUrl: './ver-autor.component.css'
})
export class VerAutorComponent implements OnInit{


  usuarioService = inject(UsuariosService);
  Usuario: Usuario | undefined;

  constructor(private route: ActivatedRoute, private ContA: ControladorAnunciosService) { }
  ngOnInit(): void {
    const nombreUsuario = this.route.snapshot.paramMap.get('nombreUsuario');
    if(nombreUsuario){
      this. cargarDatosUsuario(nombreUsuario);
    }
  }
  cargarDatosUsuario(nombreUsuario: string) {
    this.usuarioService.obtenerDatosUsuario(nombreUsuario).subscribe({
      next: (usuario: Usuario) => {
        this.Usuario = usuario;  
      },
      error: (error) => {
        console.error('Error al obtener los datos del usuario:', error); 
      },
      complete: () => {
        console.log('Petición finalizada con éxito');
      }
    });
  
}
regresar() {
  history.back();
}
}
