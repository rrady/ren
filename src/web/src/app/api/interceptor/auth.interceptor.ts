import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { first, mergeMap } from 'rxjs/operators';

import { select, Store } from '@ngrx/store';
import { RenState } from '../../store';
import * as authenticationSelectors from '../../store/selectors/authentication.selectors';

import { REN_APP_BACKEND_URL } from '../../app.constants';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private renStore: Store<RenState>) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!request || !request.url || (request.url.startsWith('http') && !(REN_APP_BACKEND_URL && request.url.startsWith(REN_APP_BACKEND_URL)))) {
      return next.handle(request);
    }

    const token = this.getToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(request);
  }

  private getToken(): Observable<string> {
    return this.renStore.pipe(
        select(authenticationSelectors.getToken),
        first(),
        mergeMap((accessToken: string) => {
            return of(accessToken);
        })
    );
  };
}
