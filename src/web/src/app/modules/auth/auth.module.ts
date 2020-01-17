import { NgModule } from '@angular/core';

import { SharedModule } from '@app/modules/shared/shared.module';

import { RegisterComponent } from '@app/modules/auth/register/register.component';
import { LoginComponent } from '@app/modules/auth/login/login.component';

@NgModule({
  imports: [
    SharedModule
  ],
  declarations: [
    RegisterComponent,
    LoginComponent
  ],
  exports: [
    RegisterComponent,
    LoginComponent
  ]
})
export class AuthModule { }
