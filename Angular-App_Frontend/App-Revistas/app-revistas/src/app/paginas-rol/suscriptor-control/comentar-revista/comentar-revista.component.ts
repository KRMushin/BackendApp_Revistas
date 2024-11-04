import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { Comentario } from '../../../../interfaces/Comentarios/Comentario';
import { utileriaToken } from '../../../../service/utileria-token.service';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';
import { ComentariosService } from '../../../../service/Comentarios/ComentariosService.service';
import { RevistasService } from '../../../../service/Revistas/revistas-service.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ComentariosListComponent } from '../../../component-list/comentarios-list/comentarios-list.component';
import { RevistaDatosDTO } from '../../../../interfaces/Revistas/RevistaDatosDTO';
import { ModalComponentComponent } from '../../../modal-component/modal-component.component';

@Component({
  selector: 'app-comentar-revista',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, ComentariosListComponent, ModalComponentComponent],
  templateUrl: './comentar-revista.component.html',
  styleUrl: './comentar-revista.component.css'
})
export class ComentarRevistaComponent implements OnInit{
    volver() {
      window.history.back();
    }

  @ViewChild(ModalComponentComponent) modal!: ModalComponentComponent; // Referencia al componente modal

  public comentarioForm!: FormGroup;
  public idRevista!: number;
  public tituloRevista!: string;
  public datosRevista!: RevistaDatosDTO;
  private nombreUsuario!: string;

  public comentariosUsuario!: Comentario[];

  constructor(private utileriaToken: utileriaToken,
              private controladorAnuncios: ControladorAnunciosService,
              private comentariosService: ComentariosService,
              private revistasService: RevistasService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private cdr: ChangeDetectorRef
  ){}

  ngOnInit(): void {
    
    this.cargarData();
    this.cargarForm();
    this.cargarComentariosUsuario();
  }
  cargarComentariosUsuario() {
      this.comentariosService.obtenerComentariosUsuario(this.nombreUsuario, this.idRevista).subscribe((comentarios: Comentario[]) => {
        console.log(comentarios);
        this.comentariosUsuario = comentarios; 
      });
  }


  cargarForm(){
    this.comentarioForm = this.fb.group({
      idRevista: [this.idRevista],
      nombreUsuario: [this.utileriaToken.obtenerNombreUsuario()],
      comentario: [null, Validators.required],
      fechaComentario: [null, Validators.required]
    });
  }
  cargarData(){
    this.idRevista = this.route.snapshot.params['idRevista'];
    this.tituloRevista = this.route.snapshot.params['tituloRevista'];
    const nombreUsuario = this.utileriaToken.obtenerNombreUsuario();
    if(nombreUsuario){
      this.nombreUsuario = nombreUsuario
    }
    this.revistasService.obtenerDatosRevista(this.idRevista).subscribe({
      next: (revista) => {
        console.log(revista);
        this.datosRevista = revista;
        this.evaluarAnuncios();
      }
    })
    
  }
  evaluarAnuncios(){
      console.log(this.datosRevista.bloquearAnuncios, 'debv');
      if(this.datosRevista.bloquearAnuncios){
        this.controladorAnuncios.bloquearAnuncios();
      }else{
        this.controladorAnuncios.permitirAnuncios();
    }
  }
  subirComentario() {
    console.log(this.comentarioForm.value);
    if(this.comentarioForm.invalid){
      return;
    }
    const comentario = this.comentarioForm.value;
    this.comentariosService.publicarComentario(comentario).subscribe({
      next: (comentario) => {
        this.modal.mostrarModal('Exito', 'Comentario subido correctamente');
        this.comentariosUsuario.push(comentario);
        this.cdr.detectChanges(); // Forzar detección de cambios
        this.comentarioForm.reset();
      },
      error: (error) => {
        console.error("Error al subir el comentario:", error);
      }
    });
  }

  ngOnDestroy(){
    this.controladorAnuncios.permitirAnuncios();
  }
}
