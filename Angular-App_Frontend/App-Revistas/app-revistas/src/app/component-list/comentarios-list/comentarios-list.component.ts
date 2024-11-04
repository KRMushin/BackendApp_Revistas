import { ChangeDetectionStrategy, Component, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { Comentario } from '../../../interfaces/Comentarios/Comentario';
import { CommonModule } from '@angular/common';
import { ComentariosService } from '../../../service/Comentarios/ComentariosService.service';
import { ModalComponentComponent } from '../../modal-component/modal-component.component';

@Component({
  selector: 'app-comentarios-list',
  standalone: true,
  imports: [CommonModule, ModalComponentComponent],
  templateUrl: './comentarios-list.component.html',
  styleUrl: './comentarios-list.component.css',

})
export class ComentariosListComponent{
  @ViewChild(ModalComponentComponent) modal!: ModalComponentComponent; // Referencia al componente modal

  
  @Input() comentarios: Comentario[] = [];
  @Input() autorizarEliminacion: boolean = false;
  
  constructor(private comentariosService: ComentariosService){}
  
  eliminarComentario(idComentario: number) {
    this.comentariosService.eliminarComentario(idComentario).subscribe({
      next: () => {
        alert("Comentario eliminado");
        this.comentarios = this.comentarios.filter(comentario => comentario.idComentario !== idComentario);
      },
      error: (error) => {
        console.error("Error al eliminar el comentario:", error);
      }
    });
  }


}
