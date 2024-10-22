import { Component, OnInit } from '@angular/core';
import { RevistaDatosDTO } from '../../../../interfaces/Revistas/RevistaDatosDTO';
import { EstadosRevistaService } from '../../../../service/Revistas/estados-revista.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CostoNuevoRevista } from '../../../../interfaces/Revistas/CostoNuevoRevista';

@Component({
  selector: 'app-costos-revistas',
  standalone: true,
  imports: [CommonModule, FormsModule, ],
  templateUrl: './costos-revistas.component.html',
  styleUrl: './costos-revistas.component.css'
})
export class CostosRevistasComponent implements OnInit{

  revistasActivas!: RevistaDatosDTO[];

  constructor(
    private estadosService: EstadosRevistaService,
  ) {}  
  ngOnInit(): void {
    this.cargarRevistas();
  }

  cargarRevistas() {
    this.estadosService.obtenerCostosRevistas('ACTIVA').subscribe({
      next: (data) => {
        this.revistasActivas = data;
      }
    });
  }

  modificarMantenimiento(idRevista: number, nuevoCosto: number) {
    const guardar: CostoNuevoRevista = this.crearNuevoCosto('MANTENIMIENTO', idRevista, nuevoCosto);
    
    this.estadosService.actualizarCosto(guardar).subscribe({
      next: () => {
        alert('Costo de mantenimiento modificado');
      },
      error: (error) => {
        console.log('Error al modificar el costo de mantenimiento:', error);
      },
      complete: () => {
        this.cargarRevistas();
      }
    });
  }


  modificarBloqueoAnuncios(idRevista: number, nuevoCosto: number) {
    const guardar: CostoNuevoRevista = this.crearNuevoCosto('BLOQUEO_ANUNCIOS', idRevista, nuevoCosto);

    this.estadosService.actualizarCosto(guardar).subscribe({
      next: () => {
        alert('Costo de bloqueo de anuncios modificado');
      },
      error: (error) => {
        console.log('Error al modificar el costo de bloqueo de anuncios:', error);
      },
      complete: () => {
        this.cargarRevistas();
      }
    });
  
  }

  crearNuevoCosto(tipoCosto: string, idRevista: number , costoN: number ): CostoNuevoRevista{
    let costoNuevo: CostoNuevoRevista = {
      idRevista: idRevista,
      costo: costoN,
      tipoCosto: tipoCosto
    };
    return costoNuevo;
  }


  }