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
  answers: Answer[]
}

export class Question implements IQuestion {
  public id: number;
  public text: string;
  public title: string;
  public viewCount: number;
  public createdOn: Date;
  public userId: number;
  public userName: string;
  public tags: Tag[];
  public answers: Answer[];

  constructor();
  constructor(id: number, text: string, title: string, viewCount: number, createdOn: Date, userId: number, userName: string, tags: Tag[], answers: Answer[])
  constructor(id?: number, text?: string, title?: string, viewCount?: number, createdOn?: Date, userId?: number, userName?: string, tags?: Tag[], answers?: Answer[]) {
      this.id = id || 0;
      this.text = text || "";
      this.title = title || "";
      this.viewCount = viewCount || 0;
      this.createdOn = createdOn || null;
      this.userId = userId || 0;
      this.userName = userName || "";
      this.tags = tags || [];
      this.answers = answers || [];
  }
}
