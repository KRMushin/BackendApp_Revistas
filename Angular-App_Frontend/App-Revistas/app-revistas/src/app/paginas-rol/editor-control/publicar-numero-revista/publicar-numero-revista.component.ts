import { CommonModule } from '@angular/common';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MimeTypeValidator } from '../../../../util/validador-archivos';
import { NumeroRevistasService } from '../../../../service/numero-revistas.service';
import { ModalComponentComponent } from '../../../modal-component/modal-component.component';
@Component({
  selector: 'app-publicar-numero-revista',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, ModalComponentComponent],
  templateUrl: './publicar-numero-revista.component.html',
  styleUrls: ['./publicar-numero-revista.component.css']
})
export class PublicarNumeroRevistaComponent implements OnInit{

  @ViewChild(ModalComponentComponent) modal!: ModalComponentComponent; // Referencia al componente modal
  @Output() numeroPublicado = new EventEmitter<void>(); // emitir una bandera cuando se publica un número


  idRevista!: number;
  formPublicarNumero!: FormGroup;
  archivoSeleccionado: File | null = null;
  tituloRevista?: string;
  archivoValido: boolean = true;  

  constructor(private route: ActivatedRoute,
              private fb: FormBuilder,
              private serviceNumerosRevista: NumeroRevistasService

  ) { }

  ngOnInit(): void {
    this.iniciarFormulario();
  }
  
  iniciarFormulario(): void {
    this.tituloRevista = String(this.route.snapshot.params['tituloRevista']);
    this.idRevista = Number(this.route.snapshot.params['idRevista']);
    this.formPublicarNumero = this.fb.group({
      idRevista: this.idRevista,
      tituloNumero: ['', Validators.required],  
      fechaPublicacion: ['', Validators.required]
    });
  }

  subirNumero(): void{
    if (this.formPublicarNumero.invalid || !this.archivoSeleccionado || !this.archivoValido) {
      this.modal.mostrarModal('verifique', 'Datos inválidos, por favor verifica');
      return;
    }
    const formData = new FormData();
    formData.append('file', this.archivoSeleccionado!);
    formData.append('tituloNumero', this.formPublicarNumero.get('tituloNumero')?.value);
    formData.append('fechaPublicacion', this.formPublicarNumero.get('fechaPublicacion')?.value);

    console.log('Formulario a enviar: ', formData);
      this.serviceNumerosRevista.publicarNumeroRevista(this.idRevista, formData).subscribe({
          next: (response) => {
            this.modal.mostrarModal('exito', 'Número de revista publicado exitosamente');
            this.limpiarFormulario();
            this.numeroPublicado.emit();
          },
          error: (error) => {
              // el error lo maneja el interceptor de errores
            }
     });
  }
  esFormularioValido(): boolean {
    return this.formPublicarNumero.valid && this.archivoSeleccionado !== null && this.archivoValido;
  }

  archivoSeleccionadoHandler(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      // Verificar si el archivo es un PDF
      if (file.type === 'application/pdf') {
        this.archivoSeleccionado = file;
        this.archivoValido = true;  
      } else {
        this.archivoSeleccionado = null; 
        this.archivoValido = false; 
        this.modal.mostrarModal('Error', 'Por favor, selecciona un archivo PDF válido.');
      }
    }
  }

  regresar(): void {
    window.history.back();
  }

  limpiarFormulario(): void {
    this.archivoSeleccionado = null;
    this.iniciarFormulario();
  }

  
  

}
