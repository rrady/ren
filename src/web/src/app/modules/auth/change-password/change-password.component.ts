import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl, AbstractControl } from '@angular/forms';

import { matchPasswords } from '@app/modules/shared/validators/match-passwords.validator';
import { AuthService } from '@app/services/auth.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  @Input() visible: boolean = false;
  @Output() onToggle: EventEmitter<boolean> = new EventEmitter<boolean>();
  errorMessages: string[] = [];

  private newPasswordControl = new FormControl('', Validators.required);
  private confirmNewPasswordControl = new FormControl('', [Validators.required, matchPasswords(this.newPasswordControl)]);
  changePasswordForm: FormGroup = this.formBuilder.group({
    currentPassword: ['', Validators.required],
    newPassword: this.newPasswordControl,
    confirmNewPassword: this.confirmNewPasswordControl
  });

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.errorMessages = [];
  }

  get currentPassword(): AbstractControl {
    return this.changePasswordForm.get('currentPassword');
  }

  get newPassword(): AbstractControl {
    return this.changePasswordForm.get('newPassword');
  }

  get confirmNewPassword(): AbstractControl {
    return this.changePasswordForm.get('confirmNewPassword');
  }

  onToggleModal(visible: boolean): void {
    this.visible = visible;
    this.onToggle.emit(false);
  }

  changePassword(): void {
    if ((this.currentPassword.errors && this.currentPassword.errors.required) || (this.newPassword.errors && this.newPassword.errors.required) ||
      (this.confirmNewPassword.errors && this.confirmNewPassword.errors.required)) {
      this.errorMessages.push("Please fill in all required fields.");
      return;
    } else if (this.confirmNewPassword.errors && this.confirmNewPassword.errors.passwordsDontMatch) {
      this.errorMessages.push("New passwords don't match.");
      this.newPassword.setValue('');
      this.confirmNewPassword.setValue('');
      return;
    } else {
      this.authService.changePassword(this.authService.userid, this.currentPassword.value, this.newPassword.value)
        .subscribe(
          (data) => {
            this.errorMessages = [];
            this.onToggleModal(false);
          },
          (error) => {
            if (error.error != null) {
              this.errorMessages = error.error.errorMessages;
            }
          });
    }
  }
}
