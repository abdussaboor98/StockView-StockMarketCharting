import { Component, OnInit } from "@angular/core";
import {
    FormControl,
    FormGroup,
    FormBuilder,
    Validators
} from "@angular/forms";

import { faAt, faKey } from "@fortawesome/free-solid-svg-icons";
import { UserService } from '../services/user.service';

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
        private userService: UserService
    ) {}

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ["", Validators.required],
            password: ["", Validators.required],
            rememberMe: [false]
        });
    }

    login() {
        console.log(this.loginForm);
    }
}
