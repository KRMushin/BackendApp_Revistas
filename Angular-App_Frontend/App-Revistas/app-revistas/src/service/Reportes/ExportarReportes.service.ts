import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { appSettings } from "../../settings/appSettings";
import { Observable } from "rxjs";
import { EfectividadAnuncioReporte } from "../../app/reportes/reportes-administrador/rep-efectividad-anunciantes/rep-efectividad-anunciantes.component";
import { ReporteGanancias } from "../../interfaces/Reportes/ReporteGanancias";

@Injectable({
    providedIn: 'root'
})
export class ExportarReportesService {

    private http = inject(HttpClient);
    private baseUrl: String = appSettings.apiUrl;

    exportarReporteEfectividad(datos: EfectividadAnuncioReporte[]): Observable<Blob> {
        const headers = new HttpHeaders({
          'Content-Type': 'application/json',
          'Accept': 'application/pdf'
        });
    
        return this.http.post(`${this.baseUrl}exportarAdmin/efectividadAnuncios`, datos, {
          headers: headers,
          responseType: 'blob' 
        });
      }
    
      abrirReporteEnNuevaPagina(datos: any): void {
        this.exportarReporteEfectividad(datos).subscribe({
          next: (response: Blob) => {
            const blobUrl = URL.createObjectURL(response);
      
            window.open(blobUrl, '_blank');
      
            URL.revokeObjectURL(blobUrl);
          },
          error: (error) => {
            console.error('Error al abrir el reporte:', error);
          }
        });
      }


      exportarReporteGananciasSistema(reporteGanancias: ReporteGanancias): Observable<Blob> {
        const headers = new HttpHeaders({
          'Content-Type': 'application/json',
          'Accept': 'application/pdf'
        });
      
        return this.http.post(`${this.baseUrl}exportarAdmin/gananciasSistema`, reporteGanancias, {
          headers: headers,
          responseType: 'blob'
        });
      }
      
      abrirReporteGananciasSistema(reporteGanancias: ReporteGanancias): void {
        this.exportarReporteGananciasSistema(reporteGanancias).subscribe({
          next: (response: Blob) => {
            const blobUrl = URL.createObjectURL(response);
      
            window.open(blobUrl, '_blank');
      
            URL.revokeObjectURL(blobUrl);
          },
          error: (error) => {
            console.error('Error al abrir el reporte de ganancias del sistema:', error);
          }
        });
      }
      
}