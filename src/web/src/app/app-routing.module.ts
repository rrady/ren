import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@app/modules/shared/guard/auth.guard';

import { FeedComponent } from '@app/modules/core/feed/feed.component';
import { AskComponent } from '@app/modules/core/ask/ask.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/feed',
    pathMatch: 'full'
  },
  {
    path: 'feed',
    component: FeedComponent
  },
  {
    path: 'ask',
    component: AskComponent,
    canActivate: [AuthGuard]
  }/*,
  {
    path: 'question/:id',
    component: QuestionComponent
  }*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    useHash: true,
    onSameUrlNavigation: "reload",
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
