import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../share/services/data-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: string = '';
  password: string = '';

  registerLogin: string = '';
  registerPassword: string = '';
  registerRepeat: string = '';

  logged: boolean = false;
  registerView = false;
  constructor(private router: Router, private dataService: DataService) { }

  ngOnInit() {
  }

  loginUser() {
    this.dataService.loginUser(this.login, this.password).subscribe(resp => {
      console.log(resp);
    })
  }

  registerUser() {
    if (this.registerPassword === this.registerRepeat) {
      this.dataService.registerUser(this.registerLogin, this.registerPassword).subscribe(resp => {
        console.log(resp);
      })
    }  
  }

  changeViewToRegisterForm() {
    this.logged = false;
    this.registerView = true;
  }

  changeViewToLoginForm() {
    this.logged = false;
    this.registerView = false;
  }
}
