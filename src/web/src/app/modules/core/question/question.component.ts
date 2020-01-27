import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { FormGroup, Validators, FormBuilder, AbstractControl } from '@angular/forms';

import { Question, Answer } from '@app/models';
import { AuthService, QuestionService } from '@app/services';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {
  private routerEventsSubscription: Subscription;

  public question: Question;
  public editMode: boolean = false;

  public questionForm: FormGroup = this.formBuilder.group({
    body: ['', Validators.required]
  });

  public postAnswerForm: FormGroup = this.formBuilder.group({
    answer: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private questionService: QuestionService,
    public authService: AuthService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
      this.routerEventsSubscription = this.router.events.subscribe((e: any) => {
        if (e instanceof NavigationEnd) {
          this.getQuestion();
        }
    });
    this.getQuestion();
  }

  ngOnDestroy(): void {
    this.routerEventsSubscription.unsubscribe();
  }

  get canPerformAction(): boolean {
    return this.authService.isAuthenticated && this.authService.userid === this.question.userId;
  }

  get body(): AbstractControl {
    return this.questionForm.get('body');
  }

  get answer(): AbstractControl {
    return this.postAnswerForm.get('answer');
  }

  get answers(): Answer[] {
    if (!this.question.answers) {
      return [];
    }

    return this.question.answers.sort((first: Answer, second: Answer) => {
      if (first.createdOn > second.createdOn) return 1;
      if (first.createdOn < second.createdOn) return -1;
      return 0;
    });
  }

  getQuestion(): void {
    this.route.params.subscribe(params => {
      let questionId: number = params['id'];
      this.questionService.getQuestion(questionId).subscribe(data => {
        this.question = data;
        this.body.setValue(data.text);
        if (this.authService.isAuthenticated) {
          this.questionService.updateQuestion(this.question.userId, this.question.id, this.question.title, this.body.value, this.question.viewCount + 1)
            .subscribe(error => console.log(error));
        }
      });
    });
  }

  postAnswer(): void {
    this.questionService.postAnswer(this.authService.userid, this.question.id, this.answer.value)
      .subscribe(data => {
        this.answer.setValue('');
        this.router.navigate(['/question', this.question.id]);
      },
      error => console.log(error));
  }

  edit(): void {
    this.editMode = true;
  }

  cancel(): void {
    this.editMode = false;
    this.body.setValue(this.question.text);
  }

  save(): void {
    this.editMode = false;
    this.questionService.updateQuestion(this.question.userId, this.question.id, this.question.title, this.body.value, this.question.viewCount)
      .subscribe(data => this.question.text = this.body.value, error => this.body.setValue(this.question.text));
  }

  delete(): void {
    this.questionService.deleteQuestion(this.question.id)
      .subscribe(data => this.router.navigate(['/feed']));
  }
}
