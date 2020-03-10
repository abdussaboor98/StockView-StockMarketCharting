import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit {

  constructor(private router: Router, private auth: AuthServiceService) { }

  ngOnInit() {
  }

  logout(){
    console.log('Logging out');
    this.auth.logout()
    this.router.navigate(["home"]);
    console.log('Logging out');
  }

}
