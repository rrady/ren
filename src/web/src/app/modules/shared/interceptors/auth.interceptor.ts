import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';

import { Observable, from } from 'rxjs';

import { environment } from '@environments/environment';
import { AuthService } from '@app/services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url.startsWith(environment.renApi) && request.url.endsWith('/refresh')) {
      return next.handle(request);
    }

    if (this.authService.isAuthenticated && this.authService.isTokenExpired) {
      return from(this.handleRefresh(request, next));
    }

    request = this.authRequest(request);

    return next.handle(request);
  }

  private async handleRefresh(request: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {
    await this.authService.refresh().toPromise();

    request = this.authRequest(request);

    return next.handle(request).toPromise()
  }

  private authRequest(request: HttpRequest<any>): HttpRequest<any> {
    const token = this.getToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return request;
  }

  private getToken(): string | null {
    return this.authService.currentIdentity ? this.authService.currentIdentity.accessToken : null;
  };
}
