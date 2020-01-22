import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '@app/services/auth.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  @Input() visible: boolean = false;
  @Output() onToggle: EventEmitter<boolean> = new EventEmitter<boolean>();

  changePasswordForm: FormGroup = this.formBuilder.group({
    currentPassword: ['', Validators.required],
    newPassword: ['', Validators.required],
    confirmNewPassword: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  onToggleModal(visible) {
    this.visible = visible;
    this.onToggle.emit(false);
  }

  changePassword() {
    this.authService.changePassword(this.changePasswordForm.controls.currentPassword.value,
      this.changePasswordForm.controls.newPassword.value, this.changePasswordForm.controls.confirmNewPassword.value);
  }
}
