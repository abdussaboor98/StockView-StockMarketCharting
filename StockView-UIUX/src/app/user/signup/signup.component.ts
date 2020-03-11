import { Component, OnInit } from "@angular/core";
import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { UserService } from "src/app/services/user.service";
import { Router } from "@angular/router";
import { User } from "src/app/models/users";
declare var $: any;

@Component({
    selector: "app-signup",
    templateUrl: "./signup.component.html",
    styleUrls: ["./signup.component.css"]
})
export class SignupComponent implements OnInit {
    signupForm: FormGroup;
    users: User[];
    usernameTaken: boolean = false;
    emailTaken: boolean = false;
    passwordsMatch: boolean = true;
    constructor(
        private formBuilder: FormBuilder,
        private userService: UserService,
        private router: Router
    ) {}
    ngOnInit() {
        this.userService.getAllUsers().subscribe(data => {
            this.users = data;
        });
        this.signupForm = this.formBuilder.group({
            id: [""],
            username: ["", Validators.required],
            email: ["", [Validators.required, Validators.email]],
            password: ["", Validators.required],
            rePassword: ["", Validators.required],
            phoneNo: ["", Validators.required],
            admin: [false],
            confirmed: [false]
        });
    }

    checkUsername(e) {
        this.usernameTaken = false;
        if(e.target.value !== "")
        this.userService
            .usernameExists(e.target.value)
            .subscribe(data => {
                if (data) {
                    this.signupForm.setErrors({
                        valid: false
                    });
                    this.usernameTaken = true;
                }
                else
                    this.usernameTaken = false;
            },error => {
                this.usernameTaken = false;
            });
    }

    checkEmail(e) {
        this.emailTaken = false;
        if(e.target.value !== "")
        this.userService
            .emailExists(e.target.value)
            .subscribe(data => {
                if (data) {
                    this.signupForm.setErrors({
                        valid: false
                    });
                    this.emailTaken = true;
                }
                else
                    this.emailTaken = false;
            },error => {
                this.emailTaken = false;
            });
    }

    checkPasswordMatch() {
        if (
            this.signupForm.get("password").value ==
            this.signupForm.get("rePassword").value
        ) {
            this.passwordsMatch = true;
        } else {
            this.passwordsMatch = false;
        }
    }

    onSubmit() {
        console.log(this.signupForm.value);
        this.userService.registerUser(this.signupForm.value).subscribe(data => {
            this.signupForm.reset();
            $("#successModal").modal("show");
        });
    }

    gotoOTP() {
        this.router.navigate(["otp"]);
        $("#successModal").modal("hide");
    }
}
