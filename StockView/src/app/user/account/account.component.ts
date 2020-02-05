import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/services/user.service";
import { User } from "src/app/models/users";
import { Router } from '@angular/router';

@Component({
    selector: "app-account",
    templateUrl: "./account.component.html",
    styleUrls: ["./account.component.css"]
})
export class AccountComponent implements OnInit {
    user: User;
    constructor(private userService: UserService, private router: Router) {}

    ngOnInit() {
        let id: string;
        if(sessionStorage.getItem("userId") !== null)
            id = sessionStorage.getItem("userId");
        else
            id = localStorage.getItem("userId");
        if (+id > 0) {
            this.userService.getUserById(+id).subscribe(data => {
				this.user = data;
			});
		} else this.router.navigate(['/landing']);
    }
}
