import { inject, Injectable } from "@angular/core";
import { appSettings } from "../../settings/appSettings";
import { HttpClient } from "@angular/common/http";
import { Like } from "../../interfaces/LIkes/Like";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class LikesService{
    private http = inject(HttpClient);
    private baseUrl: String = appSettings.apiUrl; // api url de la clase appSettings raiz del proyecto
  
    public obtnerLike(nombreUsuario: string, idRevista: number): Observable<Like>{
        return this.http.get<Like>(`${this.baseUrl}revistasLikes/obtner/${nombreUsuario}/${idRevista}`);
    }

    public publicarLike(like: any): Observable<any> {
        return this.http.post(`${this.baseUrl}revistasLikes/postearLike`, like);
    }
}