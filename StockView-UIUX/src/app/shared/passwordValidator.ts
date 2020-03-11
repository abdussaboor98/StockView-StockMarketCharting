import { AbstractControl, ValidatorFn } from '@angular/forms';


export function hasNumeric(control: AbstractControl): { [key: string]: any } | null {
    const hasNumeric = /\d/.test(control.value);
    return hasNumeric ?  null : { 'noNumeric': true };
}

export function hasSepcialCharacter(control: AbstractControl): { [key: string]: any } | null {
    const hasSpecial = /[[!@#$%^&*()_+-={};':"|,.<>]/.test(control.value);
    return hasSpecial ?  null : { 'noSepcialCharacter': true };
}

export function hasUppercase(control: AbstractControl): { [key: string]: any } | null {
    const hasUppercase = /[A-Z]/.test(control.value);
    return hasUppercase ?  null : { 'noUppercase': true };
}

export function hasLowercase(control: AbstractControl): { [key: string]: any } | null {
    const hasUppercase = /[a-z]/.test(control.value);
    return hasUppercase ?  null : { 'noLowercase': true };
}