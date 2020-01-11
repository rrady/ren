import { Action } from "@ngrx/store";
import {
    JsonWebToken,
    Credentials
} from "../../api/models";

export enum AuthenticationActionTypes {
    Login = "LOGIN",
    LoginSuccess = "LOGIN_SUCCESS",
    LoginError = "LOGIN_ERROR",

    Register = "REGISTER",
    RegisterSuccess = "REGISTER_SUCCESS",
    RegisterError = "REGISTER_ERROR",
};

export class Login implements Action {
  readonly type = AuthenticationActionTypes.Login;

  constructor(public payload: Credentials) {
  }
};

export class LoginSuccess implements Action {
  readonly type = AuthenticationActionTypes.LoginSuccess;

  constructor(public payload: JsonWebToken) {
  }
};

export class LoginError implements Action {
  readonly type = AuthenticationActionTypes.LoginError;

  constructor(public payload: string) {
  }
};

export class Register implements Action {
  readonly type = AuthenticationActionTypes.Register;

  constructor(public payload: Credentials) {
  }
};

export class RegisterSuccess implements Action {
  readonly type = AuthenticationActionTypes.RegisterSuccess;

  constructor() {
  }
};

export class RegisterError implements Action {
  readonly type = AuthenticationActionTypes.RegisterError;

  constructor(public payload: string) {
  }
};

export type AuthenticationActions = Login | LoginSuccess | LoginError | Register | RegisterSuccess | RegisterError;
