import { Etiqueta } from "./Etiqueta";

export interface Revista{

    idCategoria: number;
    idEtiquetas: Etiqueta[];
    nombreRevista: string;
    descripcion: string;
    fechaPublicacion: Date;
    nombreAutor: string;

}