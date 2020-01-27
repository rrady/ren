import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';

import { Answer, Comment } from '@app/models';
import { AuthService, QuestionService } from '@app/services';

@Component({
  selector: 'app-answer',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})
export class AnswerComponent implements OnInit {
  @Input() questionId: number;
  @Input() answer: Answer;

  public editMode: boolean = false;
  public commentMode: boolean = false;

  public answerForm: FormGroup = this.formBuilder.group({
    body: ['', Validators.required]
  });

  public postCommentForm: FormGroup = this.formBuilder.group({
    comment: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private questionService: QuestionService,
    public authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.body.setValue(this.answer.text);
  }

  get canPerformAction(): boolean {
    return this.authService.isAuthenticated && this.authService.userid === this.answer.userId;
  }

  get body(): AbstractControl {
    return this.answerForm.get('body');
  }

  get comment(): AbstractControl {
    return this.postCommentForm.get('comment');
  }

  get comments(): Comment[] {
    if (!this.answer.comments) {
      return [];
    }

    return this.answer.comments.sort((first: Comment, second: Comment) => {
      if (first.createdOn > second.createdOn) return 1;
      if (first.createdOn < second.createdOn) return -1;
      return 0;
    });
  }

  edit(): void {
    this.editMode = true;
  }

  cancel(): void {
    this.editMode = false;
    this.body.setValue(this.answer.text);
  }

  save(): void {
    this.editMode = false;
    this.questionService.updateAnswer(this.authService.userid, this.questionId, this.answer.id, this.body.value, this.answer.rating)
      .subscribe(data => this.answer.text = this.body.value, error => this.body.setValue(this.answer.text));
  }

  delete(): void {
    this.editMode = false;
    this.questionService.deleteAnswer(this.questionId, this.answer.id)
      .subscribe(data => this.router.navigate(['/question', this.questionId]));
  }

  rate(positive: boolean): void {
    if (this.authService.isAuthenticated) {
      if (positive) {
        this.answer.rating += 1;
      } else {
        this.answer.rating -= 1;
      }

      this.questionService.updateAnswer(this.authService.userid, this.questionId, this.answer.id, this.body.value, this.answer.rating)
        .subscribe(error => console.log(error));
    }
  }

  addComment(): void {
    this.commentMode = true;
  }

  postComment(): void {
    this.questionService.postComment(this.authService.userid, this.questionId, this.answer.id, this.comment.value)
      .subscribe(
        data => {
          this.comment.setValue('');
          this.commentMode = false;
          this.router.navigate(['/question', this.questionId]);
        },
        error => {
          console.log(error);
        });
  }
}
