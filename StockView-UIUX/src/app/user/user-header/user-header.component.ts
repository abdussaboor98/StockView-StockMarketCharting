import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUser } from "@fortawesome/free-solid-svg-icons"
import { AuthServiceService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.css']
})
export class UserHeaderComponent implements OnInit {

  faUser = faUser;
  constructor(private router: Router, private auth: AuthServiceService) { }

  ngOnInit() {
  }

  logout(){
    this.auth.logout();
    this.router.navigate(["home"]);
  }
}
