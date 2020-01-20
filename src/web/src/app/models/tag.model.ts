interface ITag {
  id: number,
  text: string
}

export class Tag implements ITag {
  constructor(public id: number, public text: string) {
    this.id = id;
    this.text = text;
  }
}
