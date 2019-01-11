import { Component, ViewChild } from '@angular/core';
import { User } from './share/dtos/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'webclient';
  
  static user: User;
  constructor(private router: Router) {
  }

  logout() {
    window.localStorage.removeItem('userID');
    if (this.router.url === '/') {
      window.location.reload();
    } else {
      this.router.navigate(['']);
    }
  }
}
