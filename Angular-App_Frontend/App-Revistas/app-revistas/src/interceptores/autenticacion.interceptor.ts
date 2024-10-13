import { HttpInterceptorFn } from '@angular/common/http';

export const autenticacionInterceptor: HttpInterceptorFn = (req, next) => {

  if ((req.url.indexOf("login") > 0) || (req.url.indexOf("registrar") > 0)) {
    return next(req);
  }

  //a cada request se le invoca el constructor de tokens 

  const token = localStorage.getItem("token");
  const clonReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });
  return next(clonReq);
};
