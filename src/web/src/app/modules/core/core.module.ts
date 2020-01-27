import { NgModule } from '@angular/core';

import { SharedModule } from '@app/modules/shared/shared.module';
import { AuthModule } from '@app/modules/auth/auth.module';

import { NavBarComponent } from '@app/modules/core/nav-bar/nav-bar.component';
import { SearchBoxComponent } from '@app/modules/core/search-box/search-box.component';
import { FeedComponent } from '@app/modules/core/feed/feed.component';
import { AskComponent } from '@app/modules/core/ask/ask.component';
import { CommentComponent } from '@app/modules/core/comment/comment.component';
import { AnswerComponent } from '@app/modules/core/answer/answer.component';
import { QuestionComponent } from '@app/modules/core/question/question.component';

@NgModule({
  imports: [
    SharedModule,
    AuthModule
  ],
  declarations: [
    NavBarComponent,
    SearchBoxComponent,
    FeedComponent,
    AskComponent,
    CommentComponent,
    AnswerComponent,
    QuestionComponent
  ],
  exports: [
    NavBarComponent,
    SearchBoxComponent,
    FeedComponent,
    AskComponent,
    CommentComponent,
    AnswerComponent,
    QuestionComponent
  ]
})
export class CoreModule { }
