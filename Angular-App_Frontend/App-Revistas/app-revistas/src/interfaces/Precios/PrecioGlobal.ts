import { TipoPrecioGlobal } from "../../app/enum/TipoPrecioGlobal";

export interface PrecioGlobal {
    idPrecio: number;
    precioGlobal: number; 
    modeloPrecio: TipoPrecioGlobal;
}