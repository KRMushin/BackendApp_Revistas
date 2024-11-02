import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';
import { Categoria } from '../../interfaces/Revistas/Categoria';
import { Etiqueta } from '../../interfaces/Revistas/Etiqueta';

@Injectable({
  providedIn: 'root'
})
export class CategoriasService {
  
  private http = inject(HttpClient);
  private baseApiUrl = appSettings.apiUrl;

  constructor() { }

  public obtnerCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.baseApiUrl}categorias`);
  }
  public obtnerCategoriaEtiqueta(idCategoria: number): Observable<Categoria> {
    console.log(idCategoria + "idCategoria")
    return this.http.get<Categoria>(`${this.baseApiUrl}categorias/${idCategoria}/datosCategoria`);
  }

  public obtenerCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.baseApiUrl}categorias/obtnerTodas`);
  }

  public obtenerEtiquetas(): Observable<Etiqueta[]>{
    return this.http.get<Etiqueta[]>(`${this.baseApiUrl}categorias/obtnerTodasEtiquetas`);
  }

}
