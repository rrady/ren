import { Component, OnInit } from '@angular/core';
import { Question } from '@app/models/question.model';
import { QuestionService } from '@app/services/question/question.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  questions: Question[];
  currentPage: number;

  public pgSize: number = 10;

  constructor(public questionService: QuestionService) { }

  ngOnInit() {
    this.getPage(0);
  }



  getPage(index: number): void {
    var response = this.questionService.query(index, this.pgSize).pipe(map(data => data.body));
    console.log(response);
  }
}
