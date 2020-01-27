interface IComment {
  id: number,
  text: string,
  createdOn: Date,
  creatorId: number,
  creatorName: string
}

export class Comment implements IComment {
  public id: number;
  public text: string;
  public createdOn: Date;
  public creatorId: number;
  public creatorName: string;

  constructor();
  constructor(id: number, text: string, createdOn: Date, creatorId: number, creatorName: string);
  constructor(id?: number, text?: string, createdOn?: Date, creatorId?: number, creatorName?: string) {
    this.id = id || 0;
    this.text = text || "";
    this.createdOn = createdOn || null;
    this.creatorId = creatorId || 0;
    this.creatorName = creatorName || "";
  }
}
