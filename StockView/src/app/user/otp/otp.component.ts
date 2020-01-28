import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-otp',
  templateUrl: './otp.component.html',
  styleUrls: ['./otp.component.css']
})
export class OtpComponent implements OnInit {

  otpForm : FormGroup
  constructor(private formBuilder : FormBuilder) { }

  ngOnInit() {
    this.otpForm = this.formBuilder.group({
      otp: ['',Validators.required]
    })
  }

  verifyOTP(){
    console.log(this.otpForm);
  }

}
