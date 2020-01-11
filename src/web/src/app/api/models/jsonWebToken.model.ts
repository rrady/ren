interface IJsonWebToken {
  accessToken: string,
  refreshToken: string
}

export class JsonWebToken implements IJsonWebToken {
  constructor(public accessToken: string, public refreshToken: string) {
    this.accessToken = accessToken ? accessToken : null;
    this.refreshToken = refreshToken ? refreshToken : null;
  }
}
