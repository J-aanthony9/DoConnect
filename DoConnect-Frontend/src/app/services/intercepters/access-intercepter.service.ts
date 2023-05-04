import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, of, throwError } from 'rxjs';
import { StorageService } from '../storage.service';

@Injectable({
  providedIn: 'root'
})
export class AccessIntercepterService implements HttpInterceptor {

  constructor(private route: Router,
    private storageService: StorageService) { }

  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    //handle your auth error or rethrow
    if (err.status === 401 || err.status === 403 ) {

      this.storageService.logout();
      //navigate /delete cookies or whatever
      this.route.navigateByUrl(`/home`);
      // if you've caught / handled the error, you don't want to rethrow it unless you also want downstream consumers to have to handle it as well.
      return of(err.message); // or EMPTY may be appropriate here
    }
    return throwError(() => new Error("access denied"));
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      // Clone the request to add the new header.
      const authReq = req.clone({headers: req.headers.set("Content-Type", "application/json")});
      // catch the error, make specific functions for catching specific errors and you can chain through them with more catch operators
      return next.handle(authReq).pipe(catchError(x=> this.handleAuthError(x)));
  }
}
