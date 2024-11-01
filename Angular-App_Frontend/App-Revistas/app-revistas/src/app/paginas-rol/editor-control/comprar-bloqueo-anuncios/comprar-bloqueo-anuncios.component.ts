import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompraBloqueo } from '../../../../interfaces/Revistas/Compras/CompraBloqueo';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { utileriaToken } from '../../../../service/utileria-token.service';
import { CarteraDigital } from '../../../../interfaces/Usuarios/CarteraDigital';
import { CarteraDigitalService } from '../../../../service/CarteraDigital/cartera-service.service';
import { CommonModule } from '@angular/common';
import { LlaveRevista } from '../../../../interfaces/Revistas/LlaveRevista';
import { LlavesRevistaService } from '../../../../service/Revistas/llaves-revista.service';
import { ConfiguracionesRevista } from '../../../../interfaces/Revistas/ConfiguracionesRevista';
import { RevistasService } from '../../../../service/Revistas/revistas-service.service';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';
import { Location } from '@angular/common';
import { ComprasRevistaService } from '../../../../service/Revistas/compras-revista-service';
import { ModalComponentComponent } from "../../../modal-component/modal-component.component";
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-comprar-bloqueo-anuncios',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, ModalComponentComponent],
  templateUrl: './comprar-bloqueo-anuncios.component.html',
  styleUrl: './comprar-bloqueo-anuncios.component.css'
})
export class ComprarBloqueoAnunciosComponent implements OnInit{

  @ViewChild(ModalComponentComponent) modal!: ModalComponentComponent; // Referencia al componente modal

  
  private route = inject(ActivatedRoute);
  private fb = inject(FormBuilder);
  private utileriaToken = inject(utileriaToken);
  private carteraDigitalService = inject(CarteraDigitalService);
  private llavesRevistaService = inject(LlavesRevistaService);
  private controladorAnuncios = inject(ControladorAnunciosService);
  private location = inject(Location); 
  private serviceRevista = inject(RevistasService);
  private comprasRevistaService = inject(ComprasRevistaService)

  idRevista!: number;
  revistaDatos?: LlaveRevista;
  compraBloqueoForm!: FormGroup;
  compraBloqueo!: CompraBloqueo;
  carteraDigital!: CarteraDigital;
  configRevista?: ConfiguracionesRevista;

  ngOnInit(): void {
    this.idRevista = Number(this.route.snapshot.params['idRevista']);
    this.iniciarFormulario();
    this.cargarDatosCartera();
    this.cargarDataRevista();
  }

  cargarDatosCartera(): void {
    const nombreUsuario = this.utileriaToken.obtenerNombreUsuario();
    if(nombreUsuario != null){
      this.carteraDigitalService.obtenerDatosCartera(nombreUsuario).subscribe((data: CarteraDigital) => {
        this.carteraDigital = data;
      });
    }
  }

  cargarDataRevista(): void {
    if(this.idRevista != null){
      this.llavesRevistaService.obtenerLlavePorIdRevista(this.idRevista).subscribe((data: LlaveRevista) => {
        this.revistaDatos = data;
      });

     this.serviceRevista.obtenerConfiguracionesRevista(this.idRevista).subscribe((data: ConfiguracionesRevista) => {
        this.configRevista = data;
        
        if(data.anunciosBloqueados){
          this.controladorAnuncios.bloquearAnuncios();
        }else{
          this.controladorAnuncios.permitirAnuncios();
        }
      });
    }
  }

  iniciarFormulario(): void {
    this.compraBloqueoForm = this.fb.group({
      idRevista: [this.idRevista, Validators.required],
      fechaCompra: [null, Validators.required],
      nombreUsuario: this.utileriaToken.obtenerNombreUsuario(),
      diasCompra: [null, [Validators.required, Validators.min(1)]],
    });
  }

  verificarBloqueoAnuncios(): void {
    if(this.configRevista?.anunciosBloqueados){
      this.controladorAnuncios.bloquearAnuncios();
    }else{
      this.controladorAnuncios.permitirAnuncios();
    }
  }

  subirCompra(): void {
    this.compraBloqueo = this.compraBloqueoForm.value;
    if(this.revistaDatos != null){
      const costoTotal = this.revistaDatos?.costoBloqueoAnuncios * this.compraBloqueoForm.value.duracionDias;
      if(costoTotal > this.carteraDigital.cantidadDinero){
        return;
      }
      this.compraBloqueo.costoTotal = costoTotal;
         this.comprasRevistaService.comprarBloqueoRevista(this.compraBloqueo).subscribe({
          next: () => {
            this.modal.mostrarModal('COMPRA EXITOSA', 'Compra del bloqueo en anuncios exitosa.');
            this.cargarDatosCartera();
            this.volver();
          },
          error: (error: HttpErrorResponse) => {
            if (error.status === 409) {
              this.modal.mostrarModal('Error de compra', 'DINERO INSUFICIENTE, recarga tu cartera para realizar la compra.');
            }
          }
         });
      }
  }
  volver(): void {
    this.location.back();
  }

  limpiarFormulario(): void {
    this.compraBloqueoForm.reset();
  }

  ngOnDestroy(): void {
    this.controladorAnuncios.permitirAnuncios();
  }
}

