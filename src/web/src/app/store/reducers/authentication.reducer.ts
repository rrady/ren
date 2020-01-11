import { AuthenticationActions, AuthenticationActionTypes } from '../actions/authentication.actions';
import {
  JsonWebToken,
  Credentials
} from "../../api/models";

export interface AuthenticationState {
  accessToken: string | null;
  error: string | null;
};

export const initialState: AuthenticationState = {
  accessToken: "",
  error: ""
};

function initializeAuthentication(state: AuthenticationState = initialState): AuthenticationState {
  return {
      ...state,
      error: ""
  };
};

function authenticationSuccess(state: AuthenticationState = initialState, data: JsonWebToken): AuthenticationState {
  return {
      ...state,
      accessToken: data.accessToken,
      error: ""
  };
};

function authenticationFailed(state: AuthenticationState = initialState, data: string): AuthenticationState {
  return {
      ...state,
      accessToken: "",
      error: data
  };
};

export function reducer(state: AuthenticationState = initialState, action: AuthenticationActions): AuthenticationState {
  switch (action.type) {
      case AuthenticationActionTypes.Login:
      case AuthenticationActionTypes.Register:
          return initializeAuthentication(state);

      case AuthenticationActionTypes.LoginSuccess:
          return authenticationSuccess(state, action.payload);
      case AuthenticationActionTypes.RegisterSuccess:
          return state;

      case AuthenticationActionTypes.LoginError:
      case AuthenticationActionTypes.RegisterError:
          return authenticationFailed(state, action.payload);

      default:
          return state;
  }
};

export const getToken = (state: AuthenticationState) => state.accessToken;
export const getIsAuthenticated = (state: AuthenticationState) => !!state.accessToken;
