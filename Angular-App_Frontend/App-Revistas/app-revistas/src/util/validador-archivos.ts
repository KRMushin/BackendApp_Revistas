import { AbstractControl, ValidatorFn } from '@angular/forms';

export function MimeTypeValidator(allowedMimeType: string): ValidatorFn {
  return (control: AbstractControl) => {
    if (!control.value) {
      return null;
    }

    const file = control.value as File;

    // Validar si es un archivo
    if (file && file instanceof File) {
      const mimeValid = file.type === allowedMimeType;
      
      // También verificar la extensión del archivo como respaldo
      const validExtension = file.name.toLowerCase().endsWith('.pdf');

      // Si el MIME o la extensión no es válida, devolver error
      if (!mimeValid || !validExtension) {
        return { invalidMimeType: true };
      }
    }

    return null;
  };
}
