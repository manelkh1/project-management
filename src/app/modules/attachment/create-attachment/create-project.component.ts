import { UserService } from '../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { CreateProject } from '../../../models/CreateProject';
import { Project } from '../../../models/project';
import { User } from '../../../models/user';
import { ConvertDateService } from '../../../services/convert-date.service';
import { ProjectService } from '../../../services/project.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.scss'],
})
export class CreateProjectComponent implements OnInit {
  project: Project = new Project();
  //objet createProject
  createProject: CreateProject = new CreateProject();
  form!: FormGroup;
  searchUsers:User[] = [];
  users:User[] = [];
  newDate = new Date();
  files: any[] = [];
  constructor(
    private projectService: ProjectService,
    private userService:UserService,
    private _fb: FormBuilder,
    private convertDateService: ConvertDateService,
    private router:Router
  ) {}


  ngOnInit(): void {
    this.form = this._fb.group({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
    });
  }


  addProject() {
    this.project.title = this.form.value.title;
    this.project.description = this.form.value.description;
    this.project.startDate = this.convertDateService.convert(this.form.value.startDate);
    this.project.endDate =  this.convertDateService.convert(this.form.value.endDate);
    this.project.creationDate = this.convertDateService.convert(this.newDate);
    this.createProject.project = this.project;
    this.createProject.users = this.users;

    this.projectService.addProject(this.createProject, this.files).subscribe((data: { id: any; }) => {


      this.form.reset();
      // redirect the path from create-project to project-details
      this.router.navigate(['/default/project-details', data.id])

    });
  }


  //find user by the keyword inserted
  findMember(keyword: any){
    //if there is a written value
    if(keyword.target.value.length > 1){

     /*  this.userService.getMembers(keyword.target.value.toLowerCase()).subscribe((data: User[]) =>{

        this.searchUsers = data;

        console.log(this.searchUsers)
      }); */
    }else{
      this.searchUsers = [];
    }
  }


//add the selected user to the users table
  addUser(user: User){

    const toSelect = this.users.find(c => c.id ===  user.id);
    if(toSelect == null){
      this.users.push(user);
    }
  }


//************************************** add multiple files ***************/
  fileBrowseHandler(event:any) {

   for(var i=0; i<event.target.files.length; i++){
    this.files.push(event.target.files[i]);
   }

  }


  //**************splice is to remove the files from the multiple files******** */
  deleteFile(file: any){
    for(let i=0; i < this.files.length; i++){
      if(this.files[i] === file){
        this.files.splice(i, 1);
      }
    }
  }
}
