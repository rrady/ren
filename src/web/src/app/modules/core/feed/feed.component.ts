import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { QuestionService } from '@app/services/question.service';
import { Page, Question } from '@app/models';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  questionsPage: Page<Question> = new Page<Question>();

  public pgSize: number = 10;

  constructor(public questionService: QuestionService, public route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.queryPage(0);
    });
    this.queryPage(0);
  }

  queryPage(index: number): void {
    let searchTerm: string;
    this.route.queryParams.subscribe(params => searchTerm = params['term']);
    this.questionService.query(index, this.pgSize, searchTerm)
      .subscribe(data => {
        this.questionsPage = data;
      });
  }
}
