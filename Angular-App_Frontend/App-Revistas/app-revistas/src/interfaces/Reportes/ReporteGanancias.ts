export interface ReporteGanancias {
    ingresosEditores: { totalIngresos: number; compras: CompraR[] };
    ingresosAnuncios: { totalIngresos: number; anuncios: AnuncioR[] };
    costosRevista: { totalCostosMantenimiento: number; revistas: RevistaR[] };
    totalIngresos: number;
    totalCostos: number;
    totalGanancia: number;
  }


  interface CompraR {
    idRevista: number | null;
    fechaCompra: Date;
    diasCompra: number;
    nombreUsuario: string;
    costoTotal: number;
  }
  
  interface AnuncioR {
    idAnuncio: number | null;
    nombreUsuario: string;
    fechaCompra: Date;
    tipoAnuncio: string;
    precioTotal: number;
    anuncioHabilitado: boolean;
    diasDuracion: number;
  }
  
  interface RevistaR {
    idRevista: number | null;
    tituloRevista: string;
    nombreAutor: string;
    costoMantenimiento: number;
    fechaCreacion: Date;
    bloquearAnuncios: boolean;
  }