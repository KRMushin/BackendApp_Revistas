import { jwtDecode } from "jwt-decode";

export class utileriaToken{

    public obtenerNombreUsuario(): string | null {

      const token = localStorage.getItem('token');

        if (!token) return null;
  
        try {
          const tokenValor: any = jwtDecode(token);  // Decodificar el token
          const nombreUsuario = tokenValor.sub || null;  
          return nombreUsuario;
        } catch (e) {
          console.error('Error decoding token:', e);
          return null;
        }
      }

    esEntero(numero: number): boolean {
        return Number.isInteger(numero);
      }
    
    public esNumero(valor: any): boolean {
      return typeof valor === 'number' && !isNaN(valor);
    }  
}