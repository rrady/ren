import {
  createFeatureSelector,
  createSelector
} from "@ngrx/store";

import * as authenticationReducer from "../reducers/authentication.reducer";

export const selectAuthenticationState = createFeatureSelector<authenticationReducer.AuthenticationState>("auth");

export const getToken = createSelector(
  selectAuthenticationState,
  authenticationReducer.getToken
);

export const getIsAuthenticated = createSelector(
  selectAuthenticationState,
  authenticationReducer.getIsAuthenticated
);
