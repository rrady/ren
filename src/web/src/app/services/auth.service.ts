import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import * as jwt_decode from 'jwt-decode';

import { JsonWebToken } from '@app/models/jsonWebToken.model';
import { environment } from '@environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
    constructor(private http: HttpClient, private router: Router) { }

    public get currentIdentity(): JsonWebToken | null {
      return JSON.parse(localStorage.getItem('ren-auth'))
    }

    public get isAuthenticated(): boolean {
      if (this.currentIdentity !== null)
        return true;

      return false;
    }

    get userid(): number {
      return Number(jwt_decode(this.currentIdentity.accessToken)["sub"]);
    }

    get username(): string {
      return jwt_decode(this.currentIdentity.accessToken)["username"];
    }

    get isTokenExpired(): boolean | null {
      if (this.isAuthenticated) {
        let expirationTimestamp: number = jwt_decode(this.currentIdentity.accessToken)["exp"];
        let expirationDate: Date = new Date(expirationTimestamp * 1000);

        if (expirationDate < new Date(Date.now()))
          return true;

        return false;
      }

      return null;
    }

    register(username: string, email: string, password: string): Observable<any> {
      return this.http.post<any>(`${environment.renApi}/auth/sign-up`, { username, email, password });
    }

    login(email: string, password: string): Observable<any> {
      return this.http.post<any>(`${environment.renApi}/auth/sign-in`, { email, password })
        .pipe(map(auth => {
          localStorage.setItem('ren-auth', JSON.stringify(auth));
          return auth;
        }));
    }

    refresh(): Observable<any> {
      return this.http.post<any>(`${environment.renApi}/auth/${this.currentIdentity.refreshToken}/refresh`, null)
        .pipe(map(auth => {
          localStorage.setItem('ren-auth', JSON.stringify(auth));
          return auth;
        }));
    }

    changePassword(userId: number, currentPassword: string, newPassword: string): Observable<any> {
      return this.http.put<any>(`${environment.renApi}/auth/reset`, { userId, currentPassword, newPassword });
    }

    logout(): Observable<any> {
      return new Observable(observer => {
        localStorage.removeItem('ren-auth');
        observer.complete();
      });
    }
}
