export interface IUser {
  id: number,
  email: string
}

export class User implements IUser {
  constructor(public id: number, public email: string) {
    this.id = id ? id : null;
    this.email = email ? email : null;
  }
}
