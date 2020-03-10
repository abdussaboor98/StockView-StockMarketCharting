import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/users';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  user: User;
  username:string;
  constructor(private userService: UserService) { }

  ngOnInit() {
    // let id: string;
    //     if(sessionStorage.getItem("userId") !== null){
    //       id = sessionStorage.getItem("userId");
    //     }
    //     else{
    //       id = localStorage.getItem("userId");
    //     }
    //     if (+id > 0) {
    //         this.userService.getUserById(+id).subscribe(data => {
		// 		this.user = data;
    //   });
    // }
    this.username = sessionStorage.getItem("username");
  }

}
