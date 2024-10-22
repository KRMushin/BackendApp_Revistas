import { HttpInterceptorFn } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {

  const snackBar = inject(MatSnackBar);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let errorMensaje = '';
      
      switch (error.status) {
        case 400: // Bad Request
          errorMensaje = 'Los datos introducidos son incorrectos. Por favor, verifique e intente nuevamente.';
          break;
        case 401: // Unauthorized
          errorMensaje = 'No autorizado. Debe iniciar sesión para acceder a este recurso.';
          break;
        case 404: // Not Found
          errorMensaje = 'El recurso solicitado no fue encontrado. Verifique la URL o el identificador.';
          break;
        case 409: // Conflict
          errorMensaje = 'Conflicto en la solicitud. Los datos proporcionados ya existen o son inválidos para la operación.';
          break;
        case 500: // Internal Server Error
          errorMensaje = 'Error en el servidor. Intente nuevamente más tarde.';
          break;
        default: // Otros errores no manejados específicamente
          errorMensaje = `Error inesperado: ${error.message}. Código de error: ${error.status}`;
          break;
      }
  
      snackBar.open(errorMensaje, 'Cerrar', {
        duration: 5000, 
        horizontalPosition: 'center',
        verticalPosition: 'top',
        panelClass: ['custom-snackbar']
      });

      return throwError(() => new Error(errorMensaje));
    })
  );
};
