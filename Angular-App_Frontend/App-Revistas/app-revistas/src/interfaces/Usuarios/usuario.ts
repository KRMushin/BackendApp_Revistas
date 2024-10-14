export interface Usuario {
    nombreUsuario: string;
    password: string;
    rolUsuario: string;
    nombrePila: string;
    descripcion: string;
    preferenciasUsuario?: {
        idPreferencia: number;
        nombreUsuario: string;
        tipoPreferencia: string;
        valorPreferencia: string;
    }[];
}