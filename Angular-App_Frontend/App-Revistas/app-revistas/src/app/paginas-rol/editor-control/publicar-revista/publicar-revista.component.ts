import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Revista } from '../../../../interfaces/Revistas/Revista';
import { RevistasService } from '../../../../service/Revistas/revistas-service.service';
import { Categoria } from '../../../../interfaces/Revistas/Categoria';
import { Etiqueta } from '../../../../interfaces/Revistas/Etiqueta';
import { CategoriasService } from '../../../../service/Revistas/categorias-service.service';
import { utileriaToken } from '../../../../service/utileria-token.service';
import { CommonModule } from '@angular/common';
import { concatMap, identity } from 'rxjs';
import { ControladorAnunciosService } from '../../../../service/Anuncios/controlador-anuncios.service';

@Component({
  selector: 'app-publicar-revista',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './publicar-revista.component.html',
  styleUrl: './publicar-revista.component.css'
})
export class PublicarRevistaComponent implements OnInit{
  publicacionRevistaForm!: FormGroup;
  Revista!: Revista;
  NombreUsuario!: string | null;
  categorias: Categoria[] = [];
  etiquetasCategoria!: Etiqueta[];
  archivoPDF: File | null = null;


  constructor(private formBuilder: FormBuilder,
              private revistasService: RevistasService,
              private categoriasService: CategoriasService,
              private utileriaToken: utileriaToken,
              private controladorAnuncios: ControladorAnunciosService) {}
  
  ngOnInit(): void {
    this.controladorAnuncios.permitirAnuncios();
    this.publicacionRevistaForm = this.formBuilder.group({
        idCategoria: [null, [Validators.required]],
        tituloRevista: [null, [Validators.required]],
        descripcion: [null, [Validators.required]],
        fechaPublicacion: [null, [Validators.required]],
        idEtiquetas: this.formBuilder.array([], Validators.required)  
    });

    this.categoriasService.obtnerCategorias().subscribe(categorias => {
      this.categorias = categorias;

    });

    this.publicacionRevistaForm.get('idCategoria')?.valueChanges.subscribe(value => {
      const valorNumerico = +value;
      this.publicacionRevistaForm.get('idCategoria')?.setValue(valorNumerico, { emitEvent: false });
    });

    this.NombreUsuario = this.utileriaToken.obtenerNombreUsuario();
  }


onFileChange(event: any): void {
  const file = event.target.files[0]; 
    this.archivoPDF = file; 
    this.publicacionRevistaForm.patchValue({
      archivoRevistaPDF: file.name 
    });
  }


  esArchivoValido(): boolean {
    if (this.archivoPDF) {
    
      if (this.archivoPDF.type === 'application/pdf') {
        console.log('Archivo PDF válido:', this.archivoPDF);
        return true;
      } else {
        alert('El archivo no es un PDF');
        return false;
      }
    } else {
      alert('Por favor suba un archivo PDF');
      return false;
    }

  }

subir(): void {

  if(this.publicacionRevistaForm.invalid){
    alert('Por favor llene todos los campos');
    return;
  }
  if(!this.esArchivoValido){
    alert('Por favor suba un archivo PDF');
    return;
  }
  if(!this.NombreUsuario){
    alert('No se ha podido obtener el nombre de usuario');
    return;
  }
    const RevistaEnviar = this.publicacionRevistaForm.value as Revista;
    RevistaEnviar.nombreAutor = this.NombreUsuario;
  console.log('Formulario de publicación de revista:', this.publicacionRevistaForm.value);

  this.revistasService.publicarRevista(RevistaEnviar).pipe(
    concatMap((idRevista: number) => {
      console.log('ID de la revista publicada:', idRevista);
      return this.revistasService.publicarRevistaPDF(this.archivoPDF as File, idRevista);
    })
  ).subscribe({
    next: () => {
      this.archivoPDF = null;  
      console.log('Publicación y PDF subidos con éxito');
      alert('Publicación y PDF subidos con éxito');
      this.limpiar();
    },
    error: (error) => {
      console.error('Error en alguna de las operaciones', error);
      alert('Error al procesar la operación');
    }
  });
}

  cambiarSegunCategoria(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    const idCategoria = +selectElement.value;
    const categoriaSeleccionada = this.categorias.find(categoria => categoria.idCategoria === idCategoria);
  
    const etiquetasArray: FormArray = this.publicacionRevistaForm.get('idEtiquetas') as FormArray;
    etiquetasArray.clear();  
  
    if (categoriaSeleccionada && categoriaSeleccionada.etiquetas) {
      this.etiquetasCategoria = categoriaSeleccionada.etiquetas;
    } else {
      this.etiquetasCategoria = [];
    }
  }
  
  onEtiquetaChange(event: any) {
    const etiquetaId = +event.target.value;
    const etiquetasArray: FormArray = this.publicacionRevistaForm.get('idEtiquetas') as FormArray;
  
    if (event.target.checked) {
      etiquetasArray.push(this.formBuilder.control(etiquetaId));
    } else {
      const index = etiquetasArray.controls.findIndex(x => x.value === etiquetaId);
      if (index !== -1) {
        etiquetasArray.removeAt(index);
      }
    }
  }
  ngOnDestroy(): void {
    this.controladorAnuncios.recargarAnuncios();
  }
  limpiar(): void {
    this.publicacionRevistaForm.reset();
  }
}
