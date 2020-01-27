import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';

import { Comment } from '@app/models';
import { AuthService, QuestionService } from '@app/services';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  @Input() questionId: number;
  @Input() answerId: number;
  @Input() comment: Comment;

  public editMode: boolean = false;

  public commentForm: FormGroup = this.formBuilder.group({
    body: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private questionService: QuestionService,
    private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.body.setValue(this.comment.text);
  }

  get canPerformAction(): boolean {
    return this.authService.isAuthenticated && this.authService.userid === this.comment.creatorId;
  }

  get body(): AbstractControl {
    return this.commentForm.get('body');
  }

  edit(): void {
    this.editMode = true;
  }

  cancel(): void {
    this.editMode = false;
    this.body.setValue(this.comment.text);
  }

  save(): void {
    this.editMode = false;
    this.questionService.updateComment(this.authService.userid, this.questionId, this.answerId, this.comment.id, this.body.value)
      .subscribe(data => this.comment.text = this.body.value, error => this.body.setValue(this.comment.text));
  }

  delete(): void {
    this.questionService.deleteComment(this.questionId, this.answerId, this.comment.id)
      .subscribe(data => this.router.navigate(['/question', this.questionId]));
  }
}
