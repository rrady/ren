import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import * as jwt_decode from 'jwt-decode';

import { JsonWebToken } from '@app/models/jsonWebToken.model';
import { environment } from '@environments/environment';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
    private currentAuthSubject: BehaviorSubject<JsonWebToken>;
    public currentAuth: Observable<JsonWebToken>;

    constructor(private http: HttpClient, private router: Router) {
      this.currentAuthSubject = new BehaviorSubject<JsonWebToken>(JSON.parse(localStorage.getItem('ren-auth')));
      this.currentAuth = this.currentAuthSubject.asObservable();
    }

    public get currentAuthValue(): JsonWebToken {
      return this.currentAuthSubject.value;
    }

    get isAuthenticated(): boolean {
      if (this.currentAuthSubject.value)
        return true;

      return false;
    }

    get username() {
      return jwt_decode(this.currentAuthSubject.value.accessToken)["username"];
    }

    register(username: string, email: string, password: string) {
      return this.http.post<any>(`${environment.renApi}/auth/sign-up`, { username, email, password });
    }

    login(email: string, password: string) {
      return this.http.post<any>(`${environment.renApi}/auth/sign-in`, { email, password })
        .pipe(map(auth => {
          localStorage.setItem('ren-auth', JSON.stringify(auth));
          this.currentAuthSubject.next(auth);
          return auth;
        }));
    }

    refresh() {
      return this.http.post<any>(`${environment.renApi}/auth/${this.currentAuthSubject.value.refreshToken}/refresh}`, null)
        .pipe(map(auth => {
          localStorage.setItem('ren-auth', JSON.stringify(auth));
          this.currentAuthSubject.next(auth);
          return auth;
        }));
    }

    changePassword(userId: number, currentPassword: string, newPassword: string) {
      this.http.put<any>(`${environment.renApi}/auth/reset}`, { userId, currentPassword, newPassword });
    }

    logout() {
      localStorage.removeItem('ren-auth');
      window.location.reload(); // use router
    }
}
