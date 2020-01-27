import { Comment } from '@app/models/comment.model';

interface IAnswer {
  id: number,
  text: string,
  rating: number,
  createdOn: Date,
  editedOn: Date,
  userId: number,
  userName: String,
  comments: Comment[]
}

export class Answer implements IAnswer {
  public id: number;
  public text: string;
  public rating: number;
  public createdOn: Date;
  public editedOn: Date;
  public userId: number;
  public userName: String;
  public comments: Comment[];

  constructor();
  constructor(id: number, text: string, rating: number, createdOn: Date, editedOn: Date, userId: number, userName: string, comments: Comment[]);
  constructor(id?: number, text?: string, rating?: number, createdOn?: Date, editedOn?: Date, userId?: number, userName?: string, comments?: Comment[]) {
    this.id = id || 0;
    this.text = text || "";
    this.rating = rating || 0;
    this.createdOn = createdOn || null;
    this.editedOn = editedOn || null;
    this.userId = userId || 0;
    this.userName = userName || "";
    this.comments = comments || [];
  }
}
