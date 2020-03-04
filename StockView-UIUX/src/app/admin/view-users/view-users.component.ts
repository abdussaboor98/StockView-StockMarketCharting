import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/services/user.service";
import { Router } from "@angular/router";
import { User } from "src/app/models/users";
import {
    faUserMinus,
    faUserTag,
    faEdit,
    faUserEdit
} from "@fortawesome/free-solid-svg-icons";

@Component({
    selector: "app-view-users",
    templateUrl: "./view-users.component.html",
    styleUrls: ["./view-users.component.css"]
})
export class ViewUsersComponent implements OnInit {
    faTrash = faUserMinus;
    faEye = faUserTag;
    faEdit = faEdit;
    faUserEdit = faUserEdit;
    users: User[];
    constructor(private userService: UserService, private router: Router) {}

    ngOnInit() {
        this.userService.getAllUsers().subscribe(data => {
            this.users = data;
        });
    }

    deleteUser(id: number) {
        this.userService.deleteUser(id).subscribe(data => {
            this.users = this.users.filter(user => user.id !== id);
        },error =>{
            console.log(error);
        });
    }
}
