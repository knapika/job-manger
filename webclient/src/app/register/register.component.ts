import { Component, OnInit } from '@angular/core';
import { DataService } from '../share/services/data-service.service';
import { STATUS_OK } from '../share/utils/consts';
import { Router } from '@angular/router';
import { AuthService } from '../share/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerLogin: string = '';
  registerPassword: string = '';
  registerRepeat: string = '';

  constructor(private dataService: DataService, private router: Router,
    private authService: AuthService) { }

  ngOnInit() {
  }

  registerUser() {
    if (this.registerPassword === this.registerRepeat) {
      this.dataService.registerUser(this.registerLogin, this.registerPassword).subscribe(resp => {
        if (resp.status === STATUS_OK) {
          localStorage.setItem('userID', resp.data.userID.toString());
          const url = this.authService.getUrl();
          if (url) {
            this.router.navigate([url])
          } else {
            this.router.navigate(['']);
          }
        } else {
          alert("Sorry choosen login existed!");
        }
      })
    }
  }
}
