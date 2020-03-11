import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
declare var $: any;

@Component({
    selector: "app-otp",
    templateUrl: "./otp.component.html",
    styleUrls: ["./otp.component.css"]
})
export class OtpComponent implements OnInit {
    otpForm: FormGroup;
    constructor(private formBuilder: FormBuilder, private router: Router) {}

    ngOnInit() {
        this.otpForm = this.formBuilder.group({
            otp: ["", Validators.required]
        });
    }

    verifyOTP() {
        $("#login-modal").modal("show");
        this.router.navigate(["home"]);
    }
}
