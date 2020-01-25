import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';

import { AuthService } from '@app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Input() visible: boolean = false;
  @Output() onToggle: EventEmitter<boolean> = new EventEmitter<boolean>();
  errorMessage: string;

  loginForm: FormGroup = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.errorMessage = null;
  }

  get email(): AbstractControl {
    return this.loginForm.get('email');
  }

  get password(): AbstractControl {
    return this.loginForm.get('password');
  }

  onToggleModal(visible: boolean): void {
    this.visible = visible;
    this.onToggle.emit(false);
  }

  login(): void {
    if ((this.email.errors && this.email.errors.required) || (this.password.errors && this.password.errors.required)) {
      this.errorMessage = "Please fill in all required fields.";
      return;
    } else {
      this.errorMessage = null;
      this.authService.login(this.email.value, this.password.value)
        .subscribe(
          (data) => {
            this.errorMessage = null;
            this.onToggleModal(false);
          },
          (error) => {
            this.errorMessage = error.error.message;
          });
    }
  }
}
