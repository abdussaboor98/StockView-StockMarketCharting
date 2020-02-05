import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUser } from "@fortawesome/free-solid-svg-icons"

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.css']
})
export class UserHeaderComponent implements OnInit {

  faUser = faUser;
  constructor(private router: Router) { }

  ngOnInit() {
  }

  logout(){
    console.log('logging out user');
    localStorage.removeItem("userType");
    sessionStorage.removeItem("userType");
    localStorage.removeItem("userId");
    sessionStorage.removeItem("userId");
    this.router.navigate(["home"]);
  }
}
