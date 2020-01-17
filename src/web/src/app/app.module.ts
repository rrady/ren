import { NgModule } from '@angular/core';

import { SharedModule } from '@app/modules/shared/shared.module';
import { CoreModule } from '@app/modules/core/core.module';
import { AuthModule } from '@app/modules/auth/auth.module';

import { AppComponent } from '@app/app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    SharedModule,
    CoreModule,
    AuthModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
