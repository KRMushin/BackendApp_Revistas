export interface FiltroAdminDTO {
        tipoReporte: string;
        fechaInicio: Date | null;
        fechaFin: Date | null;
        tipoAnuncio: string | null;
        diasPeriodo: number | null;
        nombreAnunciante: string;
}