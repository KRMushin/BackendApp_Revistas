import { RolUsuario } from "../enum/RolUsuario";

export interface Registro {
    nombreUsuario: string;
    nombreCompleto: string;
    password: string;
    rol : RolUsuario;
}