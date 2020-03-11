import { Component, OnInit, AfterViewInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
declare var $: any;

import { faUser, faKey, faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import { UserService } from "../services/user.service";
import { Router } from "@angular/router";
import { User } from "../models/users";
import { AuthServiceService } from '../services/auth-service.service';
import { map } from 'rxjs/operators';

@Component({
    selector: "app-login",
    templateUrl: "./login.component.html",
    styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    disableButton: boolean;
    isValid: boolean = true;
    isVerified: boolean = true;
    faUser = faUser;
    faKey = faKey;
    faEye = faEye;
    faEyeSlash = faEyeSlash;
    showPassword: boolean = false;
    users: User[];
    constructor(
        private formBuilder: FormBuilder,
        private userService: UserService,
        private router: Router,
        private auth: AuthServiceService
    ) { }

    ngOnInit() {
        this.disableButton = false;
        this.loginForm = this.formBuilder.group({
            username: ["", Validators.required],
            password: ["", Validators.required],
            rememberMe: [false]
        });
    }

    login() {
        let username = this.loginForm.get("username").value;
        let password = this.loginForm.get("password").value;
        this.disableButton = true;
        this.isValid = true;
        this.isVerified = true;
        this.auth.authenticate(username, password).subscribe(data => {
            this.router.navigate(["landing"]);
            $("#login-modal").modal("hide");
            this.loginForm.reset();
            this.disableButton = false;
        },
            error => {
                this.disableButton = false;
                this.userService.usernameExists(username).subscribe(data => {
                    if (data) {
                        this.userService.isUserActive(username).subscribe(data => {
                            if(data)
                               this.isVerified = false; 
                            else
                                this.isValid = false;
                        })
                    }
                    else {
                        this.isValid = false;
                    }
                })
            })

    }
    onTogglePassword(){
        this.showPassword = !this.showPassword
    }
}
