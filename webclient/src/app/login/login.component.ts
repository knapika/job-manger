import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../share/services/data-service.service';
import { STATUS_OK } from '../share/utils/consts';
import { AuthService } from '../share/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: string = '';
  password: string = '';

  constructor(private router: Router, private dataService: DataService,
    private authService: AuthService) { }

  ngOnInit() {
  }

  loginUser() {
    this.dataService.loginUser(this.login, this.password).subscribe(resp => {
      if (resp.status === STATUS_OK) {
        localStorage.setItem('userID', resp.data.userID.toString());
        const url = this.authService.getUrl();
        if (url) {
          this.router.navigate([url]);
        } else {
          this.router.navigate(['']);
        }
      } else {
        alert("You have entered a bad login or password");
      }     
    })
  }

  changeViewToRegisterForm() {
    this.router.navigate(['/register']);
  }
}
