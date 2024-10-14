import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../interfaces/Usuarios/usuario';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private http = inject(HttpClient);
  private baseUrl: String = appSettings.apiUrl; // api url de la clase appSettings raiz del proyecto

  constructor() { }

  public obtenerDatosUsuario(nombreUsuario: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.baseUrl}datos/usuario/${nombreUsuario}`);
  }
  
  


}
