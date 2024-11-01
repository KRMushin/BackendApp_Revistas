import { Injectable } from '@angular/core';
import { jwtDecode } from "jwt-decode";

@Injectable({
  providedIn: 'root',
})

export class utileriaToken{

    public obtenerNombreUsuario(): string | null {

      const token = localStorage.getItem('token');

        if (!token) return null;
  
        try {
          const tokenValor: any = jwtDecode(token);  // Decodificar el token
          const nombreUsuario = tokenValor.sub || null;  
          return nombreUsuario;
        } catch (e) {
          console.error('Error decodificando token:', e);
          return null;
        }
      }
    
      public obtenerRol(): string | null {
        const token = localStorage.getItem('token');
    
        if (!token) return null;
    
        try {
          const tokenValor: any = jwtDecode(token);
          const rol = tokenValor.rol || null;
          return rol;
        } catch (e) {
          console.error('Error decodificando token:', e);
          return null;
        }
      }

      public autorizarEditor(): boolean {
        const rol = this.obtenerRol();
        return rol === 'EDITOR';
      }

      public autorizarSuscriptor(): boolean {
        const rol = this.obtenerRol();
        return rol === 'SUSCRIPTOR';
      }
    esEntero(numero: number): boolean {
        return Number.isInteger(numero);
      }
    
    public esNumero(valor: any): boolean {
      return typeof valor === 'number' && !isNaN(valor);
    }  
}