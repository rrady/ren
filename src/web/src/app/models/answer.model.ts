import { Comment } from '@app/models/comment.model';

interface IAnswer {
  id: number,
  text: string,
  rating: number,
  createdOn: Date,
  editedOn: Date,
  userId: number,
  userName: String
  comments: Comment[]
}

export class Answer implements IAnswer {
  constructor(public id: number, public text: string, public rating: number, public createdOn: Date,
    public editedOn: Date, public userId: number, public userName: string, public comments: Comment[]) {

      this.id = id;
      this.text = text;
      this.rating = rating;
      this.createdOn = createdOn;
      this.editedOn = editedOn;
      this.userId = userId;
      this.userName = userName;
      this.comments = comments;
    }
}
