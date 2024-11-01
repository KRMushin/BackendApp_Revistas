import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-modal-component',
  standalone: true,
  imports: [],
  templateUrl: './modal-component.component.html',
  styleUrl: './modal-component.component.css'
})
export class ModalComponentComponent {

  @Input() botonCerrar: string = 'Cerrar';
  titulo!: string;
  mensaje!: string;
  modalElement: any;

  
  mostrarModal(titulo: string, mensaje: string) {
    this.modalElement = new (window as any).bootstrap.Modal(document.getElementById('mimodal')!);
    this.modalElement.show();
    this.titulo = titulo;
    this.mensaje = mensaje;
  }
  cerrarModal() {
    if (this.modalElement) {
      this.modalElement.hide();
    }
  }

}
