import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';  // Cambiado a 'jwtDecode'

export const autGuardGuard: CanActivateFn = (route, state) => {

  const token = localStorage.getItem('token') || ""; 
  const router = inject(Router);

  if (token !== "") {
    const tokenValor: any = jwtDecode(token);
    const rolUsuario = tokenValor.rol;

    if (route.data['roles'] && route.data['roles'].indexOf(rolUsuario) === -1) {
      alert("No tienes permisos para acceder a esta página");
      localStorage.removeItem('token');
      router.navigateByUrl("/login");
      return false;
    }
    
    if (rolUsuario === "ADMINISTRADOR") {
      return true;  // Si es ADMINISTRADOR, permite el acceso.
    } else if (rolUsuario === "COMPRADOR") {
      return true;
    } else if (rolUsuario === "EDITOR") {
      return true;

    }else {
      alert("No tienes permisos para acceder a esta página");
      router.navigateByUrl("/login");
      localStorage.removeItem('token');
      return false;
    }
  } else {
    alert("No tienes permisos para acceder a esta página");
    router.navigateByUrl("/login");
    localStorage.removeItem('token');
    return false;
  }
};

