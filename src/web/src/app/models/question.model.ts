import { Tag } from '@app/models/tag.model';
import { Answer } from '@app/models/answer.model';

interface IQuestion {
  id: number,
  text: string,
  title: string,
  viewCount: number,
  createdOn: Date,
  userId: number,
  userName: string,
  tags: Tag[],
  answears: Answer[]
}

export class Question implements IQuestion {
  constructor(public id: number, public text: string, public title: string, public viewCount: number,
    public createdOn: Date, public userId: number, public userName: string, public tags: Tag[], public answears: Answer[]) {

      this.id = id;
      this.text = text;
      this.title = title;
      this.viewCount = viewCount;
      this.createdOn = createdOn;
      this.userId = userId;
      this.userName = userName;
      this.tags = tags;
      this.answears = answears;
  }
}
