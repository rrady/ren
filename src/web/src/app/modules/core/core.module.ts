import { NgModule } from '@angular/core';

import { SharedModule } from '@app/modules/shared/shared.module';
import { AuthModule } from '@app/modules/auth/auth.module';

import { NavBarComponent } from '@app/modules/core/nav-bar/nav-bar.component';

@NgModule({
  imports: [
    SharedModule,
    AuthModule
  ],
  declarations: [
    NavBarComponent
  ],
  exports: [
    NavBarComponent
  ]
})
export class CoreModule { }
