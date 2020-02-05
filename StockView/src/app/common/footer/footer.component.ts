import { Component, OnInit } from '@angular/core';
import { faTwitter,faInstagram,faFacebook,faGithub } from '@fortawesome/free-brands-svg-icons';


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  faFacebook = faFacebook
  faTwitter = faTwitter
  faInstagram = faInstagram
  faGithub = faGithub
  constructor() { }

  ngOnInit() {
  }

}
