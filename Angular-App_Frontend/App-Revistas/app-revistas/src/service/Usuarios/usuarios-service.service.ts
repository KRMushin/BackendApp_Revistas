import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../interfaces/Usuarios/usuario';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';
import { usuarioActualizado } from '../../interfaces/Usuarios/usuarioActualizado';

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
  
  public obtnerFotoPerfil(nombreUsuario: string): Observable<Blob> {
    return this.http.get<Blob>(`${this.baseUrl}datos/usuario/${nombreUsuario}/foto` , { responseType: 'blob' as 'json' });
  }
  
  obtenerUsuarios(): Observable<String[]> {
    return this.http.get<String[]>(`${this.baseUrl}datos/usuario/listarCompradores`);
  }

  actualizarFotoPerfil(formData: FormData, nombreUsuario: String): Observable<any> {
    return this.http.post(`${this.baseUrl}datos/usuario/${nombreUsuario}/guardarFoto`, formData);
  }

  actualizarDatosUsuario(usuarioActualizado: usuarioActualizado): Observable<any> {
    return this.http.put(`${this.baseUrl}datos/usuario/actualizarDatos`, usuarioActualizado);
  }



}
