import { Component, OnInit } from '@angular/core';

import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ChangePassword } from 'src/app/models/changePassword';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss'],
})
export class ChangePasswordComponent implements OnInit {
  form!: FormGroup;
  changePasswordForm: ChangePassword = new ChangePassword();
  constructor(private _fb: FormBuilder, private userService: UserService) {}

  ngOnInit(): void {
    this.form = this._fb.group({
      oldPassword: new FormControl('', [Validators.required]),
      newPassword: new FormControl('', [Validators.required]),
      confirmedNewPassword: new FormControl('', [Validators.required]),
    });
  }

  changePassword() {
    this.changePasswordForm.oldPassword = this.form.value.oldPassword;
    this.changePasswordForm.newPassword = this.form.value.newPassword;
    this.changePasswordForm.confirmedNewPassword =
      this.form.value.confirmedNewPassword;
    this.userService
      .changePassword(this.changePasswordForm)
      .subscribe((data: { id: any }) => {
        this.form.reset();
      });
  }
}
