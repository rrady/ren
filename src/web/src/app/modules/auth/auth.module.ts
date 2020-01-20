import { NgModule } from '@angular/core';

import { SharedModule } from '@app/modules/shared/shared.module';

import { RegisterComponent } from '@app/modules/auth/register/register.component';
import { LoginComponent } from '@app/modules/auth/login/login.component';
import { ChangePasswordComponent } from '@app/modules/auth/change-password/change-password.component';

@NgModule({
  imports: [
    SharedModule
  ],
  declarations: [
    RegisterComponent,
    LoginComponent,
    ChangePasswordComponent
  ],
  exports: [
    RegisterComponent,
    LoginComponent,
    ChangePasswordComponent
  ]
})
export class AuthModule { }
