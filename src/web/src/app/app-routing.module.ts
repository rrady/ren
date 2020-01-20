import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FeedComponent } from './modules/core/feed/feed.component';

const routes: Routes = [
  {
    path: 'feed',
    component: FeedComponent
  }/*,
  {
    path: 'question/:id',
    component: QuestionComponent
  }*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
