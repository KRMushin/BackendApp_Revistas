import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { EstadosRevistaService } from '../../../../service/Revistas/estados-revista.service';
import { RevistaDatosDTO } from '../../../../interfaces/Revistas/RevistaDatosDTO';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { CostoNuevoRevista } from '../../../../interfaces/Revistas/CostoNuevoRevista';

@Component({
  selector: 'app-gestion-revistas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './gestion-revistas.component.html',
  styleUrl: './gestion-revistas.component.css'
})
export class GestionRevistasComponent implements OnInit{

  revistasEnEspera!: RevistaDatosDTO[];

  constructor(
    private estadosService: EstadosRevistaService,
  ) {}  
  ngOnInit(): void {
    this.cargarRevistas();
  }

  cargarRevistas() {
    this.estadosService.obtenerCostosRevistas('EN_ESPERA').subscribe({
      next: (data) => {
        this.revistasEnEspera = data;
      }
    });
  }

  activarRevista(idRevista: number) {
    this.estadosService.activarRevista(idRevista).subscribe({
      next: (response: HttpResponse<any>) => {
        console.log('Revista activada correctamente');
        alert('Revista activada correctamente');
      },
      error: (error) => {
        console.log(error);
        alert('Error al activar la revista');
      },
      complete: () => {
        this.cargarRevistas();
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
  crearNuevoCosto(tipoCosto: string, idRevista: number , costoN: number ): CostoNuevoRevista{
    let costoNuevo: CostoNuevoRevista = {
      idRevista: idRevista,
      costo: costoN,
      tipoCosto: tipoCosto
    };
    return costoNuevo;
  }


  }


/*
  revistasActivas!: RevistaDatosDTO[];
  revistasEnEspera!: RevistaDatosDTO[];
  isLoading = false;

  constructor(
    private estadosService: EstadosRevistaService,
    private cdr: ChangeDetectorRef
  ) {}  
  ngOnInit(): void {
    
    this.cargarRevistas();
  }

  cargarRevistas() {
    this.estadosService.obtenerCostosRevistas('ACTIVA').subscribe({
      next: (data) => {
        this.revistasActivas = data;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.log(error);
      }
    });

    this.estadosService.obtenerCostosRevistas('EN_ESPERA').subscribe({
      next: (data) => {
        this.revistasEnEspera = data;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
  activarRevista(idRevista: number) {
    this.isLoading = true;  
    this.estadosService.activarRevista(idRevista).subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.status === 200) {
          console.log('Revista activada correctamente');
          this.cargarRevistas();  
        }
      },
      error: (error) => {
        console.log(error);
      },
      complete: () => {
        this.isLoading = false;  
        this.cdr.detectChanges();
      }
    });
  }

  modificarMantenimiento(idRevista: number, nuevoCosto: number) {
    // this.estadosService.modificarCostoMantenimiento(idRevista, nuevoCosto).subscribe({
    //   next: () => {
    //     console.log('Costo de mantenimiento modificado');
    //   },
    //   error: (error) => {
    //     console.log(error);
    //   }
    // });
  }

  modificarBloqueoAnuncios(idRevista: number, nuevoCosto: number) {
    // this.estadosService.modificarCostoBloqueoAnuncios(idRevista, nuevoCosto).subscribe({
    //   next: () => {
    //     console.log('Costo de bloqueo de anuncios modificado');
    //   },
    //   error: (error) => {
    //     console.log(error);
    //   }
    // });
  }
*/