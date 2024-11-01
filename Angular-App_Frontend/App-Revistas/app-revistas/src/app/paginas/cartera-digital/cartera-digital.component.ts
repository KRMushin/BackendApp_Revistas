import { Component, inject, OnInit } from '@angular/core';
import { CarteraDigitalService } from '../../../service/CarteraDigital/cartera-service.service';
import { CarteraDigital } from '../../../interfaces/Usuarios/CarteraDigital';
import { utileriaToken } from '../../../service/utileria-token.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ControladorAnunciosService } from '../../../service/Anuncios/controlador-anuncios.service';

@Component({
  selector: 'app-cartera-digital',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cartera-digital.component.html',
  styleUrls: ['./cartera-digital.component.css']
})
export class CarteraDigitalComponent implements OnInit {
  
  private serviceCartera = inject(CarteraDigitalService);
  public carteraDigital: CarteraDigital | undefined;
  private router = inject(Router);
  public montoRecarga: number = 0;

  constructor(private controladorAnuncios: ControladorAnunciosService){}
 
  ngOnInit(): void {

    const tokenUtileria = new utileriaToken();
    const nombreUsuario = tokenUtileria.obtenerNombreUsuario();

    if(nombreUsuario != null){
      this.serviceCartera.obtenerDatosUsuario(nombreUsuario).subscribe({
        next:(carteraDigital: CarteraDigital) => {
          this.carteraDigital = carteraDigital;
        },
        error: (error) => {
          alert('no se encontro su cartera digital ');
        }
      });
    }else{
      alert('Sin autorizacion ');
      this.router.navigateByUrl('/login');
      
    }
  }
  

   recargarCartera(): void {
    const tokenUtileria = new utileriaToken();
    const nombreUsuario = tokenUtileria.obtenerNombreUsuario();

    if (!this.montoRecarga || this.montoRecarga <= 0) {
      alert('Ingrese un monto válido para recargar');
      return;
    }

    if (nombreUsuario != null && this.carteraDigital) {
      const nuevaCartera: CarteraDigital = { 
        nombreUsuario: nombreUsuario, 
        cantidadDinero: this.montoRecarga 
      };
      console.log(nuevaCartera);
      this.serviceCartera.recargarCartera(nuevaCartera).subscribe({
        next: (response) => {
          alert('Recarga exitosa');
          this.montoRecarga = 0;
          
      
          this.serviceCartera.obtenerDatosUsuario(nombreUsuario).subscribe({
            next: (carteraDigital: CarteraDigital) => {
              this.carteraDigital = carteraDigital;
            }
          });
        },
        error: (error) => {
          console.log('Error al realizar la recarga');
        }
      });
    } else {
      alert('Sin autorización');
      this.router.navigateByUrl('/login');
    }
  }
  
  ngOnDestroy(): void {
    this.controladorAnuncios.recargarAnuncios();
  }

}
