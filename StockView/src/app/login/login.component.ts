import { Component, OnInit } from "@angular/core";
import {
    FormControl,
    FormGroup,
    FormBuilder,
    Validators
} from "@angular/forms";

import { faAt, faKey } from "@fortawesome/free-solid-svg-icons";
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { User } from '../models/users';

@Component({
    selector: "app-login",
    templateUrl: "./login.component.html",
    styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    isValid: boolean = true;
    faAt = faAt;
    faKey = faKey;
    constructor(
        private formBuilder: FormBuilder,
        private userService: UserService,
        private router: Router
    ) {}

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ["", Validators.required],
            password: ["", Validators.required],
            rememberMe: [false]
        });
    }

    login() {
        if(this.userService.validateUser(this.loginForm.get("username").value,this.loginForm.get("password").value)){
            if(this.loginForm.get("rememberMe").value){
                localStorage.removeItem("username");
                localStorage.setItem("username",this.loginForm.get("username").value);
                localStorage.removeItem("userType");
                localStorage.setItem("userType",this.userService.getUserType(this.loginForm.get("username").value));
                this.router.navigate[""]
            }
            else {
                sessionStorage.removeItem("username");
                sessionStorage.setItem("username",this.loginForm.get("username").value);
            }
        }
    }
}
