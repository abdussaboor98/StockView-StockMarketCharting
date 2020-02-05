import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  isAdmin: boolean;
  constructor(private userService: UserService) {
    this.isAdmin = userService.isAdmin();
   } 

  ngOnInit() {
  }

}
