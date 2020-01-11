interface ICredentials {
  email: string,
  password: string
}

export class Credentials implements ICredentials {
  constructor(public email: string, public password: string) {
    this.email = email ? email : null;
    this.password = password ? password : null;
  }
}
