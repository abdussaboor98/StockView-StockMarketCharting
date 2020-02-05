import { Component, OnInit } from "@angular/core";
import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { UserService } from "src/app/services/user.service";
import { Router } from "@angular/router";
declare var $: any;

@Component({
    selector: "app-signup",
    templateUrl: "./signup.component.html",
    styleUrls: ["./signup.component.css"]
})
export class SignupComponent implements OnInit {
    signupForm: FormGroup;
    constructor(
        private formBuilder: FormBuilder,
        private userService: UserService,
        private router: Router
    ) {}
    ngOnInit() {
        this.signupForm = this.formBuilder.group({
            id: [""],
            username: ["", Validators.required],
            email: ["", [Validators.required, Validators.email]],
            password: ["", Validators.required],
            rePassword: ["", Validators.required],
            phoneNo: ["", Validators.required],
            isAdmin: [false],
            confirmed: [false]
        });
    }

    onSubmit() {
        console.log(this.signupForm.value);
        // this.userService.registerUser(this.signupForm.value).subscribe(data => {
        //     this.signupForm.reset();
        //     $("#successModal").modal("show");
        // });
    }

    gotoOTP() {
        this.router.navigate(["otp"]);
        $("#successModal").modal("hide");
    }
}
