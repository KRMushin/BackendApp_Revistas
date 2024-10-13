import { RolUsuario } from "../app/enum/RolUsuario";

export interface Registro {
    nombreUsuario: string;
    nombreCompleto: string;
    password: string;
    rol : RolUsuario;
}