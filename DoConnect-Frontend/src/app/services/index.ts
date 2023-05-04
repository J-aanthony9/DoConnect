import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { AuthInterceptorService } from "../services/intercepters/auth-interceptor.service";
import { AccessIntercepterService } from "./intercepters/access-intercepter.service";

export const httpInterceptorProviders = [
  {
    provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true
  },
  {
    provide: HTTP_INTERCEPTORS, useClass: AccessIntercepterService, multi:true
  }

]