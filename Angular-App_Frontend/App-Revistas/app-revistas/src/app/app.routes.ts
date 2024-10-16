import { Routes } from '@angular/router';
import { LoginComponent } from './paginas/login/login.component';
import { RegistroComponent } from './paginas/registro/registro.component';
import { InicioComponent } from './paginas/inicio/inicio.component';
import { autGuardGuard } from '../guard/aut-guard.guard';
import { AdminControlComponent } from './paginas-rol/admin-control/admin-control.component';
import { CompradorControlComponent } from './paginas-rol/comprador-control/comprador-control.component';
import { AnunciosConfiguracionComponent } from './paginas-rol/admin-control/anuncios-configuracion/anuncios-configuracion.component';
import { PerfilUsuarioComponent } from './paginas/perfil-usuario/perfil-usuario.component';
import { CarteraDigitalComponent } from './paginas/cartera-digital/cartera-digital.component';
import { ComprarAnuncioComponent } from './paginas/comprar-anuncio/comprar-anuncio.component';

export const routes: Routes = [

  {path:"", component:LoginComponent},
  {path:"login", component:LoginComponent},
  {path:"registro", component:RegistroComponent}, 
  {path: "inicio", component:InicioComponent, canActivate: [autGuardGuard]},
  {path: "admin-control", component : AdminControlComponent, canActivate: [autGuardGuard], data: {roles: ['ADMINISTRADOR']}, children:[
    {path: "anuncios-configuracion", component: AnunciosConfiguracionComponent},
    {path: "perfil-usuario", component: PerfilUsuarioComponent}
  ]},
  {

    path: 'comprador-control', component: CompradorControlComponent, canActivate: [autGuardGuard], data: {roles: ['COMPRADOR']}, children: [
      {path: 'perfil-usuario', component: PerfilUsuarioComponent},
      {path: 'cartera-digital', component: CarteraDigitalComponent},
      {path: 'comprar-anuncio', component: ComprarAnuncioComponent}
  ]}


];
