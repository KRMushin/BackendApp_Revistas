import { Component, OnInit } from '@angular/core';
import { preciosGlobales } from '../../../../service/precios/PreciosGlobales.service';
import { PrecioGlobal } from '../../../../interfaces/Precios/PrecioGlobal';
import { FormArray, FormBuilder, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gestion-precios-globales',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './gestion-precios-globales.component.html',
  styleUrl: './gestion-precios-globales.component.css'
})
export class GestionPreciosGlobalesComponent implements OnInit {

  public preciosGlobales: PrecioGlobal[] = [];
  precioForm!: FormArray;

  constructor(private fb: FormBuilder, private servicePrecios: preciosGlobales) {}

  ngOnInit(): void {
    this.cargarPreciosGlobales();
  }

  cargarPreciosGlobales(): void {
    this.servicePrecios.obtenerPreciosGlobales().subscribe((precios: PrecioGlobal[]) => {
      this.preciosGlobales = precios;

      this.precioForm = this.fb.array(
        this.preciosGlobales.map(precio =>
          this.fb.group({
            nuevoPrecio: [null, [Validators.required, Validators.min(1)]]
          })
        )
      );
    });
  }

  getFormGroup(index: number): FormGroup {
    return this.precioForm.at(index) as FormGroup;
  }
  

  actualizarPrecio(index: number): void {
    const nuevoPrecio = this.precioForm.at(index)?.get('nuevoPrecio')?.value;
    if (nuevoPrecio && nuevoPrecio > 0) {
      console.log(`Actualizando precio para el ID: ${this.preciosGlobales[index].idPrecio}, Nuevo Precio: ${nuevoPrecio}`);
      
      this.servicePrecios.actualizarPrecio(this.preciosGlobales[index].idPrecio, nuevoPrecio)
        .subscribe({
          next: (response) => {
            console.log(`Precio actualizado para el ID: ${this.preciosGlobales[index].idPrecio}, Nuevo Precio: ${nuevoPrecio}`);
            this.cargarPreciosGlobales();

          },
          error: (error) => {
            alert('Error al actualizar el precio');
          },
          complete: () => {
            alert('Actualización de precio completada.');
            this.getFormGroup(index).reset();
          }
        });
    }
  }
  
  


}
