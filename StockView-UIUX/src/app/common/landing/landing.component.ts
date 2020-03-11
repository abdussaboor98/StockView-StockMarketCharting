import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  isAdmin: boolean;
  constructor(private auth: AuthServiceService) {
    this.isAdmin = auth.isAdmin();
   } 

  ngOnInit() {
  }

}
