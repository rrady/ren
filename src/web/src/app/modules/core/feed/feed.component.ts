import { Component, OnInit } from '@angular/core';

import { QuestionService } from '@app/services/question.service';
import { Page, Question } from '@app/models';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  questionsPage: Page<Question>;

  public pgSize: number = 10;

  constructor(public questionService: QuestionService) { }

  ngOnInit() {
    this.queryPage(0);
  }

  queryPage(index: number): void {
    this.questionService.query(index, this.pgSize)
      .subscribe(data => {
        this.questionsPage = data;
      });
  }
}
