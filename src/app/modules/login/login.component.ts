import { LoginResponse } from './../../models/login';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from '../../services/auth.service';
import { TokenStorageService } from '../../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})



export class LoginComponent implements OnInit {
  isLoggedIn = false;
  isLoggedFailed = false;
  message = 'Login Failed';
  form!: FormGroup;
  loginResponse: LoginResponse = new LoginResponse();
  constructor(
    private _fb: FormBuilder,
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this._fb.group({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$'), //this is for the letters (both uppercase and lowercase) and numbers validation
      ]),
    });
  }

  get f() {
    return this.form.controls;
  }
//
  login() {
    this.authService.login(this.form.value).subscribe(
      (data: any) => {
        this.loginResponse = data.data
        this.tokenStorage.saveToken(data.data.token);
        this.tokenStorage.saveUser(data.data.user);
        this.isLoggedFailed = false;
        this.isLoggedIn = true;
        //navigation to the next component Home
        this.router.navigate(['manager/home']);
      },
      // if there is an error in the login
      (err) => {
        this.isLoggedFailed = true;
      }
    );
  }
}
