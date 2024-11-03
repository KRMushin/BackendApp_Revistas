import { Component, OnInit, ViewChild } from '@angular/core';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { utileriaToken } from '../../../../service/utileria-token.service';
import { SuscripcionesService } from '../../../../service/Suscripciones/SuscripcionesService';
import { CommonModule } from '@angular/common';
import { __param } from 'tslib';
import { ActivatedRoute } from '@angular/router';
import { ModalComponentComponent } from '../../../modal-component/modal-component.component';

@Component({
  selector: 'app-revista-suscripcion',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, ModalComponentComponent],
  templateUrl: './revista-suscripcion.component.html',
  styleUrl: './revista-suscripcion.component.css'
})
export class RevistaSuscripcionComponent implements OnInit{

  @ViewChild(ModalComponentComponent) modal!: ModalComponentComponent; // Referencia al componente modal
  public formData!: FormGroup;
  public tituloRevista?: string;
  public descripcion?: string;
  private anunciosBloqueados?: boolean;
  public idRevista?: number;

  constructor(private anunciosService: ControladorAnunciosService,
              private fb: FormBuilder,
              private tokenUtileria: utileriaToken,
              private SuscripcionService: SuscripcionesService,
              private route: ActivatedRoute

  ) {}

  ngOnInit(): void {
    this.cargarDatosSuscripcion();
    if(this.idRevista){
      this.cargarFormularioSuscripcion();
    }
  }

  cargarDatosSuscripcion(): void {
    this.tituloRevista = this.route.snapshot.params['tituloRevista'];
    this.anunciosBloqueados = Boolean(this.route.snapshot.params['anunciosBloqueados']);
    this.idRevista = Number(this.route.snapshot.params['idRevista']);
    this.descripcion = this.route.snapshot.params['descripcion'];
    
  }

  cargarFormularioSuscripcion(): void {

    this.formData = this.fb.group({
      idRevista: [this.idRevista, Validators.required],
      nombreUsuario: [this.tokenUtileria.obtenerNombreUsuario(), Validators.required],
      fechaSuscripcion: [null, Validators.required],
    })

  }

  realizarSuscripcion(): void {
    if(this.formData.invalid){
      alert("Favor de llenar todos los campos");
      return;
    }
    console.log('Formulario a enviar: ', this.formData.value);
    this.SuscripcionService.suscribirseARevista(this.formData.value).subscribe({
      next: (data: any) => {
        this.modal.mostrarModal('Gracias por tu suscripcion', 'Suscripción realizada exitosamente , puedes ver todo lo relaciona a tu suscripcion en el apartado de suscripciones');
        this.regresar();
      },
      error: (error: any) => {
        console.error('fallo en suscripcion', error);
      }
    });
      
  }
  ngOnDestroy(): void {
    this.anunciosService.permitirAnuncios();
  }

  regresar() {
    window.history.back();
  }
    

}
