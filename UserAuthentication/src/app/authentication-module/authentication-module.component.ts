import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../services.service';
import { FormBuilder } from '@angular/forms';
import { User } from '../user.model';
import { Login } from '../Login.model';

@Component({
  selector: 'app-authentication-module',
  templateUrl: './authentication-module.component.html',
  styleUrls: ['./authentication-module.component.css'],
})
export class AuthenticationModuleComponent implements OnInit {
  UserForm: any;
  LoginForm: any;
  user: User;
  loginData: Login;

  constructor(
    private service: ServicesService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.initUserForm();
    this.initLoginForm();
  }


  initUserForm() {
    this.UserForm = this.formBuilder.group({
      userName: [''],
      emailId: [''],
      password: [''],
    });
  }


  initLoginForm() {
    this.LoginForm = this.formBuilder.group({
      userName: [''],
      password: [''],
    });
  }


  save() {
    this.user = this.UserForm.value;
    this.service.addUser(this.user).subscribe((res) => {});
    this.UserForm.reset();
  }

  
  login(){
    this.loginData = this.LoginForm.value;
    this.service.Login(this.loginData).subscribe((res) => {
      console.log(res);
    });
    this.LoginForm.reset();
  }


}
