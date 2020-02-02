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
        const id = sessionStorage.getItem("userId");
        if (+id > 0) {
            this.userService.getUserById(+id).subscribe(data => {
                this.accountUpdateForm.patchValue(data);
            });
        } else this.router.navigate(["view-users"]);
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
        console.log(this.accountUpdateForm.value);
        this.userService
            .updateUser(this.accountUpdateForm.value)
            .subscribe(data => {
                this.accountUpdateForm.reset();
            });
    }

    goBack() {
        this.router.navigate(["view-users"]);
    }
}
