import { NgModule } from '@angular/core';

import { SharedModule } from '@app/modules/shared/shared.module';
import { AuthModule } from '@app/modules/auth/auth.module';

import { NavBarComponent } from '@app/modules/core/nav-bar/nav-bar.component';
import { SearchBoxComponent } from '@app/modules/core/search-box/search-box.component';
import { FeedComponent } from '@app/modules/core/feed/feed.component';
import { AskComponent } from '@app/modules/core/ask/ask.component';

@NgModule({
  imports: [
    SharedModule,
    AuthModule
  ],
  declarations: [
    NavBarComponent,
    SearchBoxComponent,
    FeedComponent,
    AskComponent
  ],
  exports: [
    NavBarComponent,
    SearchBoxComponent,
    FeedComponent,
    AskComponent
  ]
})
export class CoreModule { }
