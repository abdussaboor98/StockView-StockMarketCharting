import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/services/user.service";
import { Router } from "@angular/router";
declare var $: any;

@Component({
    selector: "app-activate",
    templateUrl: "./activate.component.html",
    styleUrls: ["./activate.component.css"]
})
export class ActivateComponent implements OnInit {
    msg: string;
    constructor(private userService: UserService, private router: Router) {}

    ngOnInit() {
        let url = document.URL;
        let urlArray = url.split("?");
        let email = urlArray[1];
        email = email.substring(0, email.length - 1);
        this.userService.activateUser(email).subscribe(data => {
            if (data) {
                this.msg = "Activation successfull, now you can login.";
            } else {
                this.msg = "Already activated.";
            }
            setTimeout(() => {
              $("#login-modal").modal("show");
              this.router.navigate(["home"]);
          }, 5000);
        });
    }
}
