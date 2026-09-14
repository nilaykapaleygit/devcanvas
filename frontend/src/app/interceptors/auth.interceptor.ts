import { HttpInterceptorFn } from "@angular/common/http";



export const authInterceptor: HttpInterceptorFn = (req,next)=> {

    const username = sessionStorage.getItem('username');
    const password = sessionStorage.getItem('password');

    if(username && password){
        const credentials = btoa(`${username}): ${password}`);

        const authReq = req.clone({
            setHeaders:{Authorization: `Basic ${credentials}`}
        });

        return next(authReq);
    }
  return next(req);
}