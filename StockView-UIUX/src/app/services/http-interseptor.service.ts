import { Injectable } from '@angular/core';
import { AuthService } from './auth-service.service';
import { HttpRequest, HttpHandler, HttpInterceptor } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpInterseptorService implements HttpInterceptor {
  constructor(public auth: AuthServiceService) { }
  // request generated will be auto intercepted and info about request is available in req object
  intercept(request: HttpRequest<any>, next: HttpHandler) {
    console.log("Inside interceptor");
    if (this.auth.getAuthenticationToken()) {
      // request object cannot be directly manipulated
      // it has to be cloned
      let authenticationToken = this.auth.getAuthenticationToken();
      request = request.clone({
        setHeaders: {
          Authorization: authenticationToken
        }
      });
    }
    return next.handle(request);
  }
}
