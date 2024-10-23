import { HttpInterceptorFn } from '@angular/common/http';

export const autenticacionInterceptor: HttpInterceptorFn = (req, next) => {
  if (req.url.includes('login') || req.url.includes('registrar')) {
    console.log('Solicitud sin token');
    return next(req); 
  }
  const token = localStorage.getItem('token');

  if (token) {
    const clonReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  
    // Pasamos la solicitud clonada con el token
    return next(clonReq);
  }
  
  return next(req);
};