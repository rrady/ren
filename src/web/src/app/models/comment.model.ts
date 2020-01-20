interface IComment {
  id: number,
  text: string,
  createdOn: Date,
  userId: number,
  userName: string
}

export class Comment implements IComment {
  constructor(public id: number, public text: string, public createdOn: Date,
    public userId: number, public userName: string) {

    this.id = id;
    this.text = text;
    this.createdOn = createdOn;
    this.userId = userId;
    this.userName = userName;
  }
}
