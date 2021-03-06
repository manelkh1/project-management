import { Bank } from './../../models/bank';
import { Member } from './../../models/member';

import { MemberService } from './../../services/member.service';
import { BankService } from './../../services/bank.service';
import { Manager } from './../../models/manager';
import { ManagerService } from './../../services/manager.service';
import { Component, OnInit, Pipe, PipeTransform, VERSION } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackbarComponent } from '../../shared/snackbar/snackbar.component';
import { UserRole } from '../../models/userRole';
import { Admin } from 'src/app/models/admin';
import { AdminService } from 'src/app/services/admin.service';
@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss'],
})
export class CreateUserComponent implements OnInit {
  //roles: Role[] = [];
  form!: FormGroup;
  user: User = new User();
  admin: Admin = new Admin();
  manager: Manager = new Manager();
  member: Member = new Member();
  bank: Bank = new Bank();
  roleEnum = UserRole;

  constructor(
    private userService: UserService,
    private adminService: AdminService,
    private managerService: ManagerService,
    private bankService: BankService,
    private memberService: MemberService,
    private _snackBar: MatSnackBar,
    private _fb: FormBuilder
  ) {}

  ngOnInit(): void {
    //this.getAllRoles();
    this.form = this._fb.group({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      post: new FormControl('', [Validators.required]),
      email: new FormControl('',
      [
        Validators.required,
        Validators.pattern('[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}'),
        Validators.minLength(6),
      ]),
      password: new FormControl('',
      [
        Validators.required,
        Validators.minLength(6),
        // Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$'),
      ]),
      city: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      codePostal: new FormControl('', [Validators.required]),
      userRole: new FormControl([Validators.required]),
    });
  }

  addUser() {
    this.user.firstName = this.form.value.firstName;
    this.user.lastName = this.form.value.lastName;
    this.user.email = this.form.value.email;
    this.user.password = this.form.value.password;
    this.user.city = this.form.value.city;
    this.user.country = this.form.value.country;
    this.user.codePostal = this.form.value.codePostal;
    this.user.post = this.form.value.post;
    this.user.userRole = this.form.value.userRole;

    console.log(this.form.value.userRole == UserRole[0]);
    if (this.form.value.userRole == UserRole[0]) {
      console.log((this.admin.user = this.user));
      this.admin.user = this.user;
      this.adminService.createAdmin(this.admin).subscribe((data) => {
        this.form.reset();
        this.openSnackBar(data.message, data.type.toLowerCase(), data.type);
      });
    }

    if (this.form.value.userRole == UserRole[1]) {
      this.manager.user = this.user;
      this.managerService.createManager(this.manager).subscribe((data) => {
        this.form.reset();
        this.openSnackBar(data.message, data.type.toLowerCase(), data.type);
      });
    }
    if (this.form.value.userRole == UserRole[2]) {
      this.member.user = this.user;
      this.memberService.createMember(this.member).subscribe((data) => {
        this.form.reset();
        this.openSnackBar(data.message, data.type.toLowerCase(), data.type);
      });
    }
    if (this.form.value.userRole == UserRole[3]) {
      this.bank.user = this.user;
      this.bankService.createBank(this.bank).subscribe((data) => {
        this.form.reset();
        this.openSnackBar(data.message, data.type.toLowerCase(), data.type);
      });
    }
  }

  get f() {
    return this.form.controls;
  }

  openSnackBar(message: string, panelClass: string, type: string) {
    this._snackBar.openFromComponent(SnackbarComponent, {
      data: {
        message: message,
        type: type,
      },
      panelClass: panelClass,
      duration: 10000,
    });
  }
}
///to iterate over  Enum
@Pipe({ name: 'enumToArray' })
export class EnumToArrayPipe implements PipeTransform {
  transform(data: Object) {
    const keys = Object.keys(data);
    return keys.slice(keys.length / 2);
  }
}
