import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem('token')

    if (token == null) {
      token = ''
    }

    const req = request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })

    console.log(req.headers.get('Authorization'))

    return next.handle(req);
  }
}
