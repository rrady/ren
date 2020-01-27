import {OnInit, Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import {Router} from '@angular/router';

import {Tag} from '@app/models';
import {AuthService, QuestionService} from '@app/services';

@Component({
  selector: 'app-ask',
  templateUrl: './ask.component.html',
  styleUrls: ['./ask.component.css']
})
export class AskComponent implements OnInit {
  public errorMessages: string[] = [];
  public availableTags: Tag[] = [];
  public selectedTags: Tag[] = [];

  askForm: FormGroup = this.formBuilder.group({
    title: ['', Validators.required],
    body: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private questionService: QuestionService,
              private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.getTags();
  }

  get title(): AbstractControl {
    return this.askForm.get('title');
  }

  get body(): AbstractControl {
    return this.askForm.get('body');
  }

  getTags(): void {
    this.questionService.getAllTags().subscribe(
      (data) => {
        this.availableTags = data;
      },
      (error) => {
        this.errorMessages = error.error.errorMessages;
      });
  }

  post(): void {
    this.errorMessages = [];
    if ((this.title.errors && this.title.errors.required) || (this.body.errors && this.body.errors.required)) {
      this.errorMessages.push("Please fill in all required fields.");
    } else {
      this.errorMessages = null;
      let userId: number = this.authService.userid;
      this.questionService.postQuestion(userId, this.title.value, this.body.value, this.selectedTags).subscribe(
        (data) => {
          this.errorMessages = null;
          this.router.navigate(['/feed']);
        },
        (error) => {
          this.errorMessages = error.error.errorMessages;
        });
    }
  }

  selectTag(tag: Tag): void {
    if (!this.selectedTags.includes(tag)) {
      this.selectedTags.push(tag);
    }
  }

  unselectTag(tag: Tag): void {
    let index: number = this.selectedTags.indexOf(tag);
    if (index > -1) {
      this.selectedTags = this.selectedTags.filter((t: Tag) => t.id !== tag.id);
    }
  }
}
