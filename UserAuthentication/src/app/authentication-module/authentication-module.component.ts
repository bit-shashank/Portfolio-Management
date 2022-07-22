import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { FormBuilder } from '@angular/forms';
import { User } from '../models/user.model';
import { Token } from '../models/token';
import { NetWorthDataService } from '../services/net-worth-data.service';

@Component({
  selector: 'app-authentication-module',
  templateUrl: './authentication-module.component.html',
  styleUrls: ['./authentication-module.component.css'],
})
export class AuthenticationModuleComponent implements OnInit {
  UserForm: any;
  LoginForm: any;
  user: User;
  token: Token

  constructor(
    private service: ServicesService,
    private formBuilder: FormBuilder,
    private router: Router,
    private netDataService: NetWorthDataService
  ) {}

  ngOnInit(): void {
    this.initUserForm();
    this.initLoginForm();
  }


  initUserForm() {
    this.UserForm = this.formBuilder.group({
      username: [''],
      password: [''],
    });
  }


  initLoginForm() {
    this.LoginForm = this.formBuilder.group({
      username: [''],
      password: [''],
    });
  }


  // save() {
  //   this.user = this.UserForm.value;
  //   this.service.addUser(this.user).subscribe((res) => {});
  //   this.UserForm.reset();
  // }

  
  login(){
    this.user = this.LoginForm.value;
    console.log(this.user)
    this.service.Authenticate(this.user).subscribe((res) => {
      this.netDataService.setToken(res['token'])
      this.netDataService.setId(res['id'])
      this.service.CalculateNetWorth(res['token'],res['id']).subscribe((res1) => {
        this.netDataService.setNetData(res1)
        this.router.navigate(['/dashboard']);
      })
    })
    this.LoginForm.reset();
  }


}
