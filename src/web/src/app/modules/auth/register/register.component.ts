import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, FormControl } from '@angular/forms';

import { AuthService } from '@app/services/auth.service';
import { matchPasswords } from '@app/modules/shared/validators/match-passwords.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  @Input() visible: boolean = false;
  @Output() onToggle: EventEmitter<boolean> = new EventEmitter<boolean>();
  errorMessages: string[];

  private passwordControl = new FormControl('', Validators.required);
  private confirmPasswordControl = new FormControl('', [Validators.required, matchPasswords(this.passwordControl)]);
  registerForm: FormGroup = this.formBuilder.group({
    username: ['', Validators.required],
    email: ['', Validators.required],
    password: this.passwordControl,
    confirmPassword: this.confirmPasswordControl
  });

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.errorMessages = [];
  }

  get username(): AbstractControl {
    return this.registerForm.get('username');
  }

  get email(): AbstractControl {
    return this.registerForm.get('email');
  }

  get password(): AbstractControl {
    return this.registerForm.get('password');
  }

  get confirmPassword(): AbstractControl {
    return this.registerForm.get('confirmPassword');
  }

  onToggleModal(visible: boolean): void {
    this.visible = visible;
    this.onToggle.emit(false);
  }

  register(): void {
    if ((this.username.errors && this.username.errors.required) || (this.email.errors && this.email.errors.required) ||
    (this.password.errors && this.password.errors.required) || (this.confirmPassword.errors && this.confirmPassword.errors.required)) {
      this.errorMessages.push("Please fill in all required fields.");
      return;
    } else if (this.confirmPassword.errors && this.confirmPassword.errors.passwordsDontMatch) {
      this.errorMessages.push("Passwords don't match.");
      this.password.setValue('');
      this.confirmPassword.setValue('');
      return;
    } else {
      this.errorMessages = [];
      this.authService.register(this.username.value, this.email.value, this.password.value)
        .subscribe(
          (data) => {
            this.errorMessages = [];
            this.onToggleModal(false);
          },
          (error) => {
            this.errorMessages = error.error.errorMessages;
          });
    }
  }
}
