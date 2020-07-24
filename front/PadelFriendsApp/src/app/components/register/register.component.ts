import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UserService } from '../../services/user.service';
import { UserViewModel } from '../../models/User/UserViewModel';
import { User } from '../../models/User/User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  formValid = true;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(4)]],
      repeatpassword: ['', [Validators.required, Validators.minLength(4)]],
    });

    this.registerForm.valueChanges.subscribe(x => {
      this.formValid = (this.registerForm.value.password === this.registerForm.value.repeatpassword);
    });
  }

  register(): void {
    const userRegister = new User();
    userRegister.name = this.registerForm.value.name;
    userRegister.username = this.registerForm.value.username;
    userRegister.password = this.registerForm.value.password;
    userRegister.role = 'ROLE_PLAYER';

    this.userService.register(userRegister).subscribe(data => {
      this.router.navigate(['/login']);
    });
  }
}
