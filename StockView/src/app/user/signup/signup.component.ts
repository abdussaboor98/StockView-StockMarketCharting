import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"]
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  constructor(private formBuilder : FormBuilder, private userService : UserService) { }
  ngOnInit() { 
    this.signupForm = this.formBuilder.group({
      id : [''],
      username : ['',Validators.required], 
      email : ['',[Validators.required, Validators.email]], 
      password : ['',Validators.required], 
      rePassword : ['',Validators.required], 
      phoneNo : ['',Validators.required],
      isAdmin : ['fasle'],
      confirmed : ['false']
    });
  }

  onSubmit(){
    console.log(this.signupForm.value);
    this.userService.registerUser(this.signupForm.value).subscribe(data => {
      this.signupForm.reset();
    });
  }
}
