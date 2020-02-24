import { Component, OnInit } from "@angular/core";
import {
    FormControl,
    FormGroup,
    FormBuilder,
    Validators
} from "@angular/forms";
declare var $: any;

import { faAt, faKey } from "@fortawesome/free-solid-svg-icons";
import { UserService } from "../services/user.service";
import { Router } from "@angular/router";
import { User } from "../models/users";

@Component({
    selector: "app-login",
    templateUrl: "./login.component.html",
    styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    disableButton:boolean;
    isValid: boolean = true;
    faAt = faAt;
    faKey = faKey;
    users: User[];
    constructor(
        private formBuilder: FormBuilder,
        private userService: UserService,
        private router: Router
    ) { }

    ngOnInit() {
        this.disableButton = false;
        this.loginForm = this.formBuilder.group({
            username: ["", Validators.required],
            password: ["", Validators.required],
            rememberMe: [false]
        });
        this.userService.getAllUsers().subscribe(data => {
            this.users = data;
        });
    }

    login() {
        this.disableButton = true;
        this.isValid = true;
        localStorage.removeItem("userId");
        localStorage.removeItem("userType");
        sessionStorage.removeItem("userId");
        sessionStorage.removeItem("userType");
        if (this.validateUser(this.loginForm.get("username").value,this.loginForm.get("password").value)) {
            if (this.loginForm.get("rememberMe").value) {
                localStorage.setItem("userId",this.getUserId(this.loginForm.get("username").value).toString());
                localStorage.setItem("userType",this.getUserType(this.loginForm.get("username").value));
            }
            sessionStorage.setItem("userId",this.getUserId(this.loginForm.get("username").value).toString());
            sessionStorage.setItem("userType",this.getUserType(this.loginForm.get("username").value));
            this.router.navigate(["landing"]);
            $("#login-modal").modal("hide");
            this.loginForm.reset();
            this.disableButton = false;
        } else {
            this.isValid = false;
            this.disableButton = false;
        }
    }

    validateUser(username: string, password: string): boolean {
        for (let u of this.users) {
            if (u.username == username && u.password == password) {
                return true;
            }
        }
        return false;
    }

    getUserType(username: string): string {
        for (let user of this.users) {
            if (user.username == username) {
                if (user.admin) {
                    return "admin";
                }
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
