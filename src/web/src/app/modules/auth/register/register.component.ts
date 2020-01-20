import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '@app/services/auth/auth.service';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  @Input() visible: boolean = false;
  @Output() onToggle: EventEmitter<boolean> = new EventEmitter<boolean>();

  registerForm: FormGroup = this.formBuilder.group({
    username: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }

  onToggleModal(visible) {
    this.visible = visible;
    this.onToggle.emit(false);
  }

  register() {
    if (this.registerForm.controls.password.value === this.registerForm.controls.confirmPassword.value) {
      this.authService.register(this.registerForm.controls.username.value, this.registerForm.controls.email.value, this.registerForm.controls.password.value)
        .pipe(first())
        .subscribe(data => {
          this.router.navigate(['/feed']);
        });
    } else {
      console.log("passwords don't match");
    }
  }
}
