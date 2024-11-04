import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Comentario } from "../../interfaces/Comentarios/Comentario";
import { HttpClient } from "@angular/common/http";
import { appSettings } from "../../settings/appSettings";

@Injectable({
    providedIn: 'root'
})
export class ComentariosService {

  private http = inject(HttpClient);
  private baseUrl: String = appSettings.apiUrl; // api url de la clase appSettings raiz del proyecto

  // metodo para ver los comentarios que ha echo un usuario a una revista
  obtenerComentariosUsuario(nombreUsuario: string, idRevista: number): Observable<Comentario[]> {
    console.log(`${this.baseUrl}comentarios/usuarioComentarios/${nombreUsuario}/${idRevista}`);
    return this.http.get<Comentario[]>(`${this.baseUrl}comentarios/usuarioComentarios/${nombreUsuario}/${idRevista}`);
  }

  publicarComentario(comentario: Comentario): Observable<any> {
    return this.http.post(`${this.baseUrl}comentarios/publicarComentario`, comentario);
  }

  comentariosDeRevista(idRevista: number): Observable<Comentario[]> {
    return this.http.get<Comentario[]>(`${this.baseUrl}comentarios/comentariosRevista/${idRevista}`);
  }

  eliminarComentario(idComentario: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}comentarios/eliminarComentario/${idComentario}`);
  }

}