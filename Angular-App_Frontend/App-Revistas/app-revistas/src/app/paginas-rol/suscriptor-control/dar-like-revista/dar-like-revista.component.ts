import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ComentariosService } from '../../../../service/Comentarios/ComentariosService.service';
import { Like } from '../../../../interfaces/LIkes/Like';
import { LikesService } from '../../../../service/Comentarios/Likes.service';
import { utileriaToken } from '../../../../service/utileria-token.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dar-like-revista',
  standalone: true,
  imports: [],
  templateUrl: './dar-like-revista.component.html',
  styleUrl: './dar-like-revista.component.css'
})
export class DarLikeRevistaComponent implements OnInit,OnChanges{

  @Input() idRevista!: number;

  public like!: Like;
  public nombreUsuario!: string | null;

  constructor(private likesService: LikesService,
              private utileriaToken: utileriaToken,
              private route: ActivatedRoute
  ){}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['idRevista'] && this.nombreUsuario) {
      this.cargarLike();
    }
  }
  
  ngOnInit(): void {
    this.nombreUsuario = this.utileriaToken.obtenerNombreUsuario();
    this.idRevista = this.route.snapshot.params['idRevista']; 
    this.cargarLike();
  }

  cargarLike(){
    if(this.nombreUsuario && this.idRevista){
      this.likesService.obtnerLike(this.nombreUsuario, this.idRevista).subscribe({
        next: (response: Like) => {
          console.log(response);
          this.like = response;
        },
        error: (error) => {
          console.log(error);
        }
      });
    }
  }

  calificarRevista(arg0: number) {
    throw new Error('Method not implemented.');
    }
    alertCalificacion() {
    throw new Error('Method not implemented.');
    }
    

}
