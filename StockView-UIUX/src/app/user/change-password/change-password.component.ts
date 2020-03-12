import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { hasLowercase, hasNumeric, hasUppercase, hasSepcialCharacter, passwordMatchValidator } from 'src/app/shared/passwordValidator';
import { UserService } from 'src/app/services/user.service';
import { AuthServiceService } from 'src/app/services/auth-service.service';
declare var $:any;

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

    passwordUpdateForm: FormGroup
    message: string;
    constructor(private formBuilder: FormBuilder, 
                private route: ActivatedRoute, 
                private userService: UserService,
                private auth: AuthServiceService,
                private router: Router
    ) { }

    ngOnInit() {
        this.passwordUpdateForm = this.formBuilder.group({
            username: [this.route.snapshot.params.username],
            oldPassword: ["", Validators.required],
            newPassword: ["", [Validators.required, Validators.minLength(8), hasLowercase, hasNumeric, hasUppercase, hasSepcialCharacter]],
            reNewPassword: ["", [Validators.required, passwordMatchValidator("newPassword")]],
        })

        this.passwordUpdateForm.controls.newPassword.valueChanges.subscribe(data => {
            this.passwordUpdateForm.controls.reNewPassword.updateValueAndValidity();
        })
    }

    onSubmit() {
        this.userService.updatePassword(this.passwordUpdateForm.value).subscribe(data => {
            if (data) {
                this.message = "Password changed successfully. You will be logged out shortly.";
                $("#passwordChangeModal").modal("show")
                setTimeout(() => {
                    this.auth.logout()
                    $("#passwordChangeModal").modal("hide")
                this.router.navigate(['/home'])
                },10000)
                
            }
            else {
                this.message = "the password did not change. You might have given wrong old password."
                $("#passwordChangeModal").modal("show")
            }
        }, error => {
            this.message = "the password did not change. You might have given wrong old password."
            $("#passwordChangeModal").modal("show")
        })
    }
}


