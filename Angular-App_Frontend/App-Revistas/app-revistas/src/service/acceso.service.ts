import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { appSettings } from '../settings/appSettings';
import { Login } from '../interfaces/Login';
import { Observable } from 'rxjs';
import { ResponseAcceso } from '../interfaces/ResponseAcceso';
import { Registro } from '../interfaces/Registro';
@Injectable({
  providedIn: 'root'
})
export class AccesoService {
  
  
  private http = inject(HttpClient);
  private baseUrl: String = appSettings.apiUrl; // api url de la clase appSettings raiz del proyecto

  constructor() { }

  registrarUsuario(objeto: Registro): Observable<ResponseAcceso> {// metodo para llamar al registro de usuario
    return this.http.post<ResponseAcceso>(`${this.baseUrl}registrar`, objeto);
  }
  
  login(objeto: Login): Observable<ResponseAcceso> {// metodo para login de usuario
    return this.http.post<ResponseAcceso>(`${this.baseUrl}login/usuario`, objeto);
  }

  registrarAdministrador(formData: Registro): Observable<ResponseAcceso> {
    return this.http.post<ResponseAcceso>(`${this.baseUrl}registrar/administrador`, formData);
  }

}

