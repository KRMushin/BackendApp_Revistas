import { Component, inject, Inject, OnInit } from '@angular/core';
import { ConfiguracionAnuncioService } from '../../../service/Anuncios/configuracion-anuncio.service';
import { ConfiguracionAnuncio } from '../../../interfaces/Anuncios/Configuracion-anuncio';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarteraDigitalService } from '../../../service/CarteraDigital/cartera-service.service';
import { CarteraDigital } from '../../../interfaces/Usuarios/CarteraDigital';
import { utileriaToken } from '../../../service/utileria-token.service';

@Component({
  selector: 'app-comprar-anuncio',
  standalone: true,
  imports: [CommonModule, FormsModule, CurrencyPipe, ReactiveFormsModule],
  templateUrl: './comprar-anuncio.component.html',
  styleUrls: ['./comprar-anuncio.component.css']
})
export class ComprarAnuncioComponent implements OnInit {

  private UtileriaToken = new utileriaToken();
  private servicePrecios: ConfiguracionAnuncioService;
  private serviceCartera: CarteraDigitalService;
  
  public precios: ConfiguracionAnuncio[] = [];
  public CarteraDigital: CarteraDigital | null = null; 

  public anuncioForm: FormGroup;

  constructor(private fb: FormBuilder, servicePrecios: ConfiguracionAnuncioService, CarteraDigitalService: CarteraDigitalService) {
    this.servicePrecios = servicePrecios;
    this.serviceCartera = CarteraDigitalService;

    this.anuncioForm = this.fb.group({
      selectedAnuncio: [null, Validators.required],
      diasDeseados: [0, [Validators.required, Validators.min(1)]],
      archivoSeleccionado: [null],
      textoAnuncio: [null],
      precioTotal: [{ value: null, disabled: true }],
      fechaAnuncio: [null, Validators.required]  
    });
  }

  ngOnInit(): void {
    this.servicePrecios.obtenerConfiguracionAnuncio().subscribe((data: ConfiguracionAnuncio[]) => {
      this.precios = data;
    });
    
    const nombreUsuario = this.UtileriaToken.obtenerNombreUsuario();
    if (nombreUsuario != null) {
      this.serviceCartera.obtenerDatosCartera(nombreUsuario).subscribe((cartera: CarteraDigital) => {
        this.CarteraDigital = cartera;
      });
    }
    this.anuncioForm.get('selectedAnuncio')?.valueChanges.subscribe(value => {
      this.aplicarValidacionesDinamicas(value?.tipoAnuncio); // Aplicar validaciones dinámicas según el tipo de anuncio
      this.calcularPrecioTotal();
    });

    this.anuncioForm.get('diasDeseados')?.valueChanges.subscribe(value => {
      this.calcularPrecioTotal();
    });

  }

  calcularPrecioTotal(): void {
    const selectedAnuncio = this.anuncioForm.get('selectedAnuncio')?.value;
    const diasDeseados = this.anuncioForm.get('diasDeseados')?.value;

    if (selectedAnuncio && diasDeseados > 0 && typeof selectedAnuncio.tiempoDuracion === 'number' && this.UtileriaToken.esEntero(diasDeseados)) {
      this.anuncioForm.patchValue({ precioTotal: ((selectedAnuncio.tiempoDuracion * diasDeseados) + selectedAnuncio.precio) });
    } else {
      this.anuncioForm.patchValue({ precioTotal: null });
    }
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.anuncioForm.patchValue({ archivoSeleccionado: file });
      console.log('Archivo seleccionado:', file.name);
    }
  }

  seleccionarDias(dias: number): void {
    this.anuncioForm.patchValue({ diasDeseados: dias });
  }

  realizarCompra(): void {

    if(this.anuncioForm.invalid){
      alert('Favor de llenar todos los campos');
      return;
    }
      const formData = new FormData();

      const anuncioDTO = {
        nombreUsuario: this.CarteraDigital?.nombreUsuario,  
        tipoAnuncio: this.anuncioForm.get('selectedAnuncio')?.value.tipoAnuncio,
        diasDuracion: this.anuncioForm.get('diasDeseados')?.value,
        textoAnuncio: this.anuncioForm.get('textoAnuncio')?.value || '',
        fechaPago: this.anuncioForm.get('fechaAnuncio')?.value  
      };

      formData.append('anuncioDTO', JSON.stringify(anuncioDTO));


      const selectedAnuncio = this.anuncioForm.get('selectedAnuncio')?.value;
      if (selectedAnuncio.tipoAnuncio === 'VIDEO' || selectedAnuncio.tipoAnuncio === 'IMAGEN_TEXTO') {
        const archivoSeleccionado = this.anuncioForm.get('archivoSeleccionado')?.value;
        if (archivoSeleccionado) {
          formData.append('archivo', archivoSeleccionado);  
          formData.append('nombreArchivo', archivoSeleccionado.name);
        }
      }

      this.servicePrecios.comprarAnuncio(formData).subscribe({
        next: () => {
          alert('La compra fue un éxito');
          this.anuncioForm.reset();
        },
        error: (err) => {
          console.error('Error al realizar la compra:', err);
        }
      });

    console.log('Datos que se están enviando:');
    formData.forEach((value, key) => {
      console.log(`${key}:`, value);
    });
      
  }

  aplicarValidacionesDinamicas(tipoAnuncio: string): void {
    const textoAnuncio = this.anuncioForm.get('textoAnuncio');
    const archivoSeleccionado = this.anuncioForm.get('archivoSeleccionado');
  
    if (tipoAnuncio === 'TEXTO') {
      textoAnuncio?.setValidators([Validators.required, Validators.minLength(10)]);
      archivoSeleccionado?.clearValidators();
   
    } else if (tipoAnuncio === 'VIDEO') {
      archivoSeleccionado?.setValidators([Validators.required]);
      textoAnuncio?.clearValidators();

    } else if (tipoAnuncio === 'IMAGEN_TEXTO') {
      textoAnuncio?.setValidators([Validators.required, Validators.minLength(10)]);
      archivoSeleccionado?.setValidators([Validators.required]);
    }
  
    textoAnuncio?.updateValueAndValidity();
    archivoSeleccionado?.updateValueAndValidity();
  }

}
