import { AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';

export function matchPasswords(control: AbstractControl): ValidatorFn {
  return (matchControl: AbstractControl): ValidationErrors => {
    if (!control.value && !matchControl.value) {
      return null;
    }

    return control.value === matchControl.value ? null : { passwordsDontMatch: true };
  };
}
