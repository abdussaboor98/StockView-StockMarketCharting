import { Injectable } from "@angular/core";
import {
    CanActivate,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    UrlTree,
    Router
} from "@angular/router";
import { Observable } from "rxjs";
import { AuthServiceService } from '../services/auth-service.service';
declare var $:any;

@Injectable({
    providedIn: "root"
})
export class LoginGuard implements CanActivate {
    constructor(private authService: AuthServiceService, private router: Router) {}

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ):
        | Observable<boolean | UrlTree>
        | Promise<boolean | UrlTree>
        | boolean
        | UrlTree {
        if (this.authService.isUserLoggedIn()) {
            return true;
        } else {
            alert("You need to login first!");
            this.router.navigate(["home"]);
            $("#login-modal").modal("show");
            return false;
        }
    }
}
