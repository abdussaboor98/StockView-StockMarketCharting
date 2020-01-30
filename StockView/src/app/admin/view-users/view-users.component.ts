import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/services/user.service";
import { Router } from "@angular/router";
import { User } from "src/app/models/users";

@Component({
  selector: "app-view-users",
  templateUrl: "./view-users.component.html",
  styleUrls: ["./view-users.component.css"]
})
export class ViewUsersComponent implements OnInit {
  users: User[];
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
    });
  }

  deleteUser(id) {
    this.userService.deleteUser(id).subscribe(data => {
      this.users = this.users.filter(user => user.id !== id);
    });
  }
}
