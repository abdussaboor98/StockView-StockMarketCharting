import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"]
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  constructor() { }
  ngOnInit() { 
    this.signupForm = new FormGroup({
      'name' : new FormControl(null), 
      'email' : new FormControl(null), 
      'password' : new FormControl(null), 
      'rePassword' : new FormControl((null)), 
      'phoneNo' : new FormControl(null) 
    });
  }

  onSubmit(){
    console.log(this.signupForm);
  }
}
