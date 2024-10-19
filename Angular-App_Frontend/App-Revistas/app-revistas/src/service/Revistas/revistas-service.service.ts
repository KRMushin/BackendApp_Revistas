import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { appSettings } from '../../settings/appSettings';
import { Observable } from 'rxjs';
import { Revista } from '../../interfaces/Revistas/Revista';

@Injectable({
  providedIn: 'root'
})
export class RevistasService {
  private http = inject(HttpClient);
  private baseApiUrl = appSettings.apiUrl;

  constructor() { }

  public publicarRevista(revistaDTO: Revista): Observable<number> {
    return this.http.post<number>(`${this.baseApiUrl}revistas/publicar`, revistaDTO);
  }

  public publicarRevistaPDF(revistaPDF: File, idRevista: number): Observable<boolean> {
    const formData = new FormData();
    formData.append('revistaPDF', revistaPDF);  
    return this.http.post<boolean>(`${this.baseApiUrl}revistas/${idRevista}/guardarPDF`, formData);
  }
  
  
}
