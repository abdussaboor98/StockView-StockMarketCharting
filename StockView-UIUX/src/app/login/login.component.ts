import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
declare var $: any;

import { faAt, faKey } from "@fortawesome/free-solid-svg-icons";
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
    faAt = faAt;
    faKey = faKey;
    users: User[];
    constructor(
        private formBuilder: FormBuilder,
        private userService: UserService,
        private router: Router,
        private auth:AuthServiceService
    ) {}

    ngOnInit() {
        this.disableButton = false;
        this.loginForm = this.formBuilder.group({
            username: ["", Validators.required],
            password: ["", Validators.required],
            rememberMe: [false]
        });
    }

    // login(){
    //     let username = this.loginForm.get("username").value;
    //     let password = this.loginForm.get("password").value;
    //     const result = this.auth.authenticate(username,password);
    // }

    login() {
        let username = this.loginForm.get("username").value;
        let password = this.loginForm.get("password").value;
        this.disableButton = true;
        this.isValid = true;
        this.isVerified = true;
        localStorage.removeItem("userId");
        localStorage.removeItem("userType");
        sessionStorage.removeItem("userId");
        sessionStorage.removeItem("userType");
        this.userService
            .getUserByUsernameAndPassword(username, password)
            .subscribe(
                data => {
                    if (data !== null) {
                        if (data.confirmed) {
                            if (this.loginForm.get("rememberMe").value) {
                                localStorage.setItem(
                                    "userId",
                                    data.id.toString()
                                );
                                localStorage.setItem(
                                    "userType",
                                    data.admin ? "admin" : "user"
                                );
                            }
                            sessionStorage.setItem(
                                "userId",
                                data.id.toString()
                            );
                            sessionStorage.setItem(
                                "userType",
                                data.admin ? "admin" : "user"
                            );
                            this.router.navigate(["landing"]);
                            $("#login-modal").modal("hide");
                            this.loginForm.reset();
                            this.disableButton = false;
                        } else {
                            console.log("not confirmed");
                            this.isVerified = false;
                            this.disableButton = false;
                        }
                    } else {
                        this.isValid = false;
                        this.disableButton = false;
                    }
                },
                error => {
                    this.isValid = false;
                    this.disableButton = false;
                }
            );
    }

    validateUser(username: string, password: string, user: User): boolean {
        if (user.username == username && user.password == password) {
            return true;
        }
        return false;
    }

    getUserType(isAdmin: boolean): string {
            if (user.username == username) {
                if (user.admin) {
                    return "admin";
                }
            }
        return "user";
    }

    getUserId(username: string): number {
        for (let user of this.users) {
            if (user.username == username) {
                return user.id;
            }
        }
    }
}
