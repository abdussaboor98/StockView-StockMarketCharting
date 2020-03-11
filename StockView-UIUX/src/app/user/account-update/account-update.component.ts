import { Component, OnInit } from "@angular/core";
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { UserService } from "src/app/services/user.service";
import { Router } from "@angular/router";

@Component({
    selector: "app-account-update",
    templateUrl: "./account-update.component.html",
    styleUrls: ["./account-update.component.css"]
})
export class AccountUpdateComponent implements OnInit {
    accountUpdateForm: FormGroup;
    constructor(
        private formBuilder: FormBuilder,
        private userService: UserService,
        private router: Router
    ) {}
    ngOnInit() {
        let username: string;
        username = sessionStorage.getItem("username");
        if (username) {
            this.userService.getUserByUsername(username).subscribe(data => {
                this.accountUpdateForm.patchValue(data);
            });
        } else this.router.navigate(["/landing"]);
        this.accountUpdateForm = this.formBuilder.group({
            id: [""],
            username: ["", Validators.required],
            email: ["", [Validators.required, Validators.email]],
            password: [""],
            rePassword: [""],
            phoneNo: [""],
            isAdmin: [""],
            confirmed: [""]
        });
    }

    onSubmit() {
        this.userService
            .updateUser(this.accountUpdateForm.value)
            .subscribe(data => {
                this.accountUpdateForm.reset();
                this.goBack();
            });
    }

    goBack() {
        this.router.navigate(["/account"]);
    }
}
