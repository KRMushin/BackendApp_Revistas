import { jwtDecode } from "jwt-decode";

export class utileriaToken{

    public obtenerNombreUsuario(token: string | null): string | null {
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

    public esNumero(valor: any): boolean {
      return typeof valor === 'number' && !isNaN(valor);
    }  
}