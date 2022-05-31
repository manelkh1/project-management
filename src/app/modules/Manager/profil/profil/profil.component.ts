import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Project } from 'src/app/models/project';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  id!: number;
  user: User = new User();
  projects: Project[] = [];
  form!: FormGroup;
  isEditingFirstName = false;
  isEditingLastName = false;
  isEditingEmail =false;
  isEditingPassword  =false;
  isEditingPost =false;
  isEditingCity =false;
  isEditingCountry =false;
  isEditingCodePostal =false;

  url =
    'https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp';


  constructor( private route: ActivatedRoute,
    private _fb: FormBuilder,
    private userService: UserService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.form = this._fb.group({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      post: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      codePostal: new FormControl('', [Validators.required]),
    });

    this.getUser();
    this.getProjects();
  }



  getUser() {
    // this.userService.getUserById(this.id).subscribe((data) => {
    //   this.user = data;
    //   this.form.controls['firstName'].setValue(this.user.firstName);
    //   this.form.controls['lastName'].setValue(this.user.lastName);
    //   this.form.controls['post'].setValue(this.user.post);
    //   this.form.controls['email'].setValue(this.user.email);
    //   this.form.controls['password'].setValue(this.user.password);

    //   this.form.controls['city'].setValue(this.user.city);
    //   this.form.controls['country'].setValue(this.user.country);
    //   this.form.controls['codePostal'].setValue(this.user.codePostal);
    //   console.log(this.user);
    // });
  }
  getProjects() {
    // this.userService.getProjectByUserId(this.id).subscribe((data) => {
    //   this.projects = data;
    //   console.log(this.projects);
    // });
  }
  // setEditingFirstName(mode: boolean){
  //   this.isEditingFirstName = mode;
  // }

  // cancelUpdateFirstName(){
  //   this.setEditingFirstName(false);
  //   this.form.controls['firstName'].setValue(this.user.firstName);
  // }

  setEditing(mode: boolean){
    this.isEditingFirstName = mode;
    this.isEditingLastName=mode;
    this.isEditingCity= mode;
    this.isEditingCountry=mode;
    this.isEditingCodePostal =mode;
    this.isEditingPost=mode;
    this.isEditingEmail=mode;
    this.isEditingPassword=mode;

  }

  cancelUpdate(){
    this.setEditing(false);
    this.form.controls['firstName'].setValue(this.user.firstName);
    this.form.controls['lastName'].setValue(this.user.lastName);
    this.form.controls['email'].setValue(this.user.email);
    this.form.controls['password'].setValue(this.user.password);
    this.form.controls['post'].setValue(this.user.post);
    this.form.controls['city'].setValue(this.user.city);
    this.form.controls['country'].setValue(this.user.country);
    this.form.controls['codePostal'].setValue(this.user.codePostal);
  }



  // setEditingLastName(mode: boolean){
  //   this.isEditingLastName= mode;
  // }

  // cancelUpdateLastName(){
  //   this.setEditingLastName(false);
  //   this.form.controls['lastName'].setValue(this.user.lastName);
  // }
  updateUser(){
    this.setEditing(false);
    // this.setEditingLastName(false);
    console.log(this.user)
  }
}
