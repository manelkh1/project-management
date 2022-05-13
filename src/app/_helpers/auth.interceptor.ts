
//HttpInterceptor has intercept() method to inspect and transform HTTP requests before they are sent to server.

import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenStorageService } from '../services/token-storage.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private jwt: TokenStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    const jwt = this.jwt.getToken();
    if (jwt != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + jwt) });
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
