import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { SnackbarComponent } from '../../shared/snackbar/snackbar.component';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss'],
})
export class CreateUserComponent implements OnInit {
  //roles: Role[] = [];
  form!: FormGroup;
  user: User = new User();

  constructor(private userService: UserService, 
    private _snackBar: MatSnackBar,
    private _fb: FormBuilder) {}

  ngOnInit(): void {
    //this.getAllRoles();
    this.form = this._fb.group({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      post: new FormControl('', [Validators.required]),
      email: new FormControl('', [
        Validators.required,
        Validators.pattern(
          '[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}'
        ),
        Validators.minLength(6),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
       // Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$'),
      ]),
      city: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      codePostal: new FormControl('', [Validators.required]),
      selectedRole: new FormControl( [Validators.required]),
    });
  }


/*   addUser() {
    console.log(parseInt(this.form.value.selectedRole));
    this.user.firstName = this.form.value.firstName;
    this.user.lastName = this.form.value.lastName;
    this.user.email = this.form.value.email;
    this.user.password = this.form.value.password;
    this.user.city = this.form.value.city;
    this.user.country = this.form.value.country;
    this.user.codePostal = this.form.value.codePostal;
    this.user.post = this.form.value.post;
    
     this.userService.addUser(this.user, parseInt(this.form.value.)).subscribe(data =>{
       console.log(data);
       this.form.reset();
       this.openSnackBar(data.message, data.type.toLowerCase(), data.type);

     });
  } */
  get f() {
    return this.form.controls;
  }

  openSnackBar(message: string, panelClass: string, type: string) {
    this._snackBar.openFromComponent(SnackbarComponent, {
      data: {
        message: message,
        type: type
      },
      panelClass: panelClass,
      duration: 10000
    });
  }

}
