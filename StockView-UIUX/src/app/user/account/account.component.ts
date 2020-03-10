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
    username : string;
    constructor(private userService: UserService, private router: Router) {}

    ngOnInit() {
        let id: string;
        this.username = sessionStorage.getItem("username");
        
        if (this.username!=null) {
            this.userService.getUserByUsername(this.username).subscribe(data => {
				this.user = data;
			});
		} else this.router.navigate(['/landing']);
    }
}
