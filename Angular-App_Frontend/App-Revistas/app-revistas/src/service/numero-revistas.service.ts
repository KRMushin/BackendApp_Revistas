import { inject, Injectable } from "@angular/core";
import { appSettings } from "../settings/appSettings";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { NumeroRevista } from "../interfaces/numerosRevista/NumeroRevista";

@Injectable({
    providedIn: 'root'
})
export class NumeroRevistasService {

    private http = inject(HttpClient);
    private baseApiUrl = appSettings.apiUrl;

    publicarNumeroRevista(idRevista: number, formData: FormData) {
        return this.http.post(`${this.baseApiUrl}numerosRevista/publicarNumero/${idRevista}`, formData);
    }

    public obtnerNumerosRevista(idRevista: number): Observable<NumeroRevista[]> {
        return this.http.get<NumeroRevista[]>(`${this.baseApiUrl}numerosRevista/obtenerTodos/${idRevista}`);
    }

    public verNumeroRevista(idNumeroRevista: number): Observable<Blob>{
        console.log("idNumeroRevista", idNumeroRevista);
        return this.http.get(`${this.baseApiUrl}numerosRevista/${idNumeroRevista}/archivoPDF`, { responseType: 'blob' });
    }

}