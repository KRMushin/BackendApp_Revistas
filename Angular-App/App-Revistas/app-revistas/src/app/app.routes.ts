import { Routes } from '@angular/router';
import { LoginComponent } from './paginas/login/login.component';
import { RegistroComponent } from './paginas/registro/registro.component';

export const routes: Routes = [

  {path:"login", component:LoginComponent},
  {path:"registro", component:RegistroComponent}, 

];
