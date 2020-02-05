import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';
declare var $:any;

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuard implements CanActivate {

  constructor(private userService: UserService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (this.userService.isAdmin()) {
        return true;
    } 
    else if(this.userService.isUser()){
        alert("You do not have access to this page!\nOnly admin can access");
        this.router.navigate(['landing']);
        return false;
    }
    else {
      alert("You need to login first!");
        this.router.navigate(["home"]);
        $("#login-modal").modal("show");
        return false;
    }
  }
  
}
