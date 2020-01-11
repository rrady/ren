import { ActionReducerMap } from "@ngrx/store";

import * as authenticationReducer from "./reducers/authentication.reducer";

export interface RenState {
  auth: authenticationReducer.AuthenticationState;
}

export const reducers: ActionReducerMap<RenState> = {
  auth: authenticationReducer.reducer
};
