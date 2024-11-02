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
import { EditorControlComponent } from './paginas-rol/editor-control/editor-control.component';
import { AnunciosSistemaComponent } from './paginas-rol/admin-control/anuncios-sistema/anuncios-sistema.component';
import { GestionPreciosGlobalesComponent } from './paginas-rol/admin-control/gestion-precios-globales/gestion-precios-globales.component';
import { PublicarRevistaComponent } from './paginas-rol/editor-control/publicar-revista/publicar-revista.component';
import { PublicacionesHechasComponent } from './paginas-rol/editor-control/publicaciones-hechas/publicaciones-hechas.component';
import { DetallesRevistaEditorSuscriptorComponent } from './paginas/detalles-revista-editor-suscriptor/detalles-revista-editor-suscriptor.component';
import { GestionRevistasComponent } from './paginas-rol/admin-control/gestion-revistas/gestion-revistas.component';
import { CostosRevistasComponent } from './paginas-rol/admin-control/costos-revistas/costos-revistas.component';
import { ComprarBloqueoAnunciosComponent } from './paginas-rol/editor-control/comprar-bloqueo-anuncios/comprar-bloqueo-anuncios.component';
import { NumerosRevistaRolComponent } from './paginas/numeros-revista-rol/numeros-revista-rol.component';
import { SuscriptorControlComponent } from './paginas-rol/suscriptor-control/suscriptor-control.component';
import { VerPdfComponent } from './vistas-archivos/ver-pdf/ver-pdf.component';
import { NavegarEnRevistasComponent } from './paginas-rol/suscriptor-control/navegar-en-revistas/navegar-en-revistas.component';

export const routes: Routes = [

  {path:"", component:LoginComponent},
  {path:"login", component:LoginComponent},
  {path:"registro", component:RegistroComponent}, 
  {path: "inicio", component:InicioComponent, canActivate: [autGuardGuard]},
  {path: "admin-control", component : AdminControlComponent, canActivate: [autGuardGuard], data: {roles: ['ADMINISTRADOR']}, children:[
    {path: "anuncios-configuracion", component: AnunciosConfiguracionComponent},
    {path: "perfil-usuario", component: PerfilUsuarioComponent},
    {path: 'anunciosEnSistema', component: AnunciosSistemaComponent},
    {path: 'preciosGlobales' , component: GestionPreciosGlobalesComponent},
    {path: 'gestionRevistas', component: CostosRevistasComponent},
    {path: 'activacionRevistas', component: GestionRevistasComponent}
  ]},
  {
    
    path: 'comprador-control', component: CompradorControlComponent, canActivate: [autGuardGuard], data: {roles: ['COMPRADOR']}, children: [
      {path: 'perfil-usuario', component: PerfilUsuarioComponent},
      {path: 'cartera-digital', component: CarteraDigitalComponent},
      {path: 'comprar-anuncio', component: ComprarAnuncioComponent},
      {path: 'MisCompras', component: AnunciosSistemaComponent}
    ]},
    {
      
      path: 'editor-control', 
      component: EditorControlComponent, 
      canActivate: [autGuardGuard], data: {roles: ['EDITOR']}, 
      children: [
        {path: 'perfil-usuario', component: PerfilUsuarioComponent},
        {path: 'cartera-digital', component: CarteraDigitalComponent},
        {path: 'publicarRevista', component: PublicarRevistaComponent},
        {path: 'misPublicaciones', component: PublicacionesHechasComponent},
        {path: 'misPublicaciones/detallesRevista/:idRevista', component: DetallesRevistaEditorSuscriptorComponent } ,
        {path: 'misPublicaciones/comprarBloqueoAnuncios/:idRevista', component:ComprarBloqueoAnunciosComponent},
        {path: 'misPublicaciones/publicarNumero/:idRevista/:tituloRevista', component: NumerosRevistaRolComponent},
        {path: 'misPublicaciones/verArchivoPDF/:idRevista/:tipoArchivo', component: VerPdfComponent}
    ]},
    {

      path: 'suscriptor-control', 
      component: SuscriptorControlComponent, 
      canActivate: [autGuardGuard],
      data: {roles: ['SUSCRIPTOR']}, 
      children: [
        {path: 'navegacionRevistas', component: NavegarEnRevistasComponent},
        {path: 'navegacionRevistas/detallesRevista/:idRevista', component: DetallesRevistaEditorSuscriptorComponent}
      ]

    }
];
