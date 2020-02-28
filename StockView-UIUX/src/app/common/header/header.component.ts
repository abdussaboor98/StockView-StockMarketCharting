import { Component, OnInit } from '@angular/core';
import { faHome, faUserPlus, faBars} from "@fortawesome/free-solid-svg-icons"

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  faHome = faHome;
  faSignup = faUserPlus;
  faMenu = faBars;
  constructor() { }

  ngOnInit() {
  }

}
