import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { appSettings } from "../../settings/appSettings";
import { FiltroEditorDTO } from "../../app/reportes/FiltroEditorDTO";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ReportesEditorService {

   
    private http = inject(HttpClient);
    private baseUrl: String = appSettings.apiUrl;
    
    public obtenerReporteComentarios(filtro: FiltroEditorDTO): Observable<any>{
        return this.http.post(`${this.baseUrl}ReportesEditor/reporteComentarios/revista`, filtro);
    }
    obtenerReporteSuscripciones(filtro: FiltroEditorDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}ReportesEditor/reporteSuscripciones/revista`, filtro);
    }
    obtenerReporteLikes(filtro: FiltroEditorDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}ReportesEditor/revistas/maspopulares`, filtro);
    }
    obtenerReportePagos(filtro: FiltroEditorDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}ReportesEditor/reporte/pagosRevistas`, filtro);
    }
}