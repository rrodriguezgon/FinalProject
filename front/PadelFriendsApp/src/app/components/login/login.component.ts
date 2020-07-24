import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UserService } from '../../services/user.service';
import { UserViewModel } from '../../models/User/UserViewModel';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  user: UserViewModel;

  showSpinner = false;
  showAlert = false;
  messageAlert = '';

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('player'));

    if (this.user != null) {
      this.router.navigate(['/']);
    } else {
      this.loginForm = this.formBuilder.group({
        username: ['', [Validators.required, Validators.minLength(4)]],
        password: ['', [Validators.required, Validators.minLength(4)]],
      });
    }
  }

  hideAlert(): void {
    this.showAlert = false;
  }

  login(): void {
    this.showSpinner = true;
    this.userService.login(this.loginForm.value).subscribe(
      (data) => {
        this.user = data;
        this.user.password = this.loginForm.value.password;
        localStorage.setItem('player', JSON.stringify(this.user));
        this.router.navigate(['/']);

        this.showSpinner = false;
      },
      error => {
        console.log(error);
        this.messageAlert = 'It was not possible to connect to the server or have error. ';
        this.showAlert = true;
      });
  }

}
