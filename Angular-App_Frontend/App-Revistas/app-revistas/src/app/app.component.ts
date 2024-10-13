import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'app-revistas';
  mostrarNav = false;

  constructor(private router: Router ){

    this.router.events.subscribe((event) =>{
      if(event instanceof NavigationEnd){
        this.mostrarNav = event.url === '/' || event.url === '/login' || event.url === '/registro';
      }
      }
    )
  }
}
