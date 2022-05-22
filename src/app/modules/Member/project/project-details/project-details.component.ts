import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Attachment } from 'src/app/models/attachement';
import { Invitation } from 'src/app/models/invitation';
import { Project } from 'src/app/models/project';
import { Status } from 'src/app/models/status';
import { User } from 'src/app/models/user';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.scss'],
})
export class ProjectDetailsComponent implements OnInit {
  id!: number;
  project: Project = new Project();
  isEditingTitle = false;
  form!: FormGroup;
  attachement: Attachment[] = [];
  invitations: Invitation[] = [];
  files: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private _fb: FormBuilder,
    private projectService: ProjectService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.form = this._fb.group({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
    });
    this.getProject();
    this.getAttachments();
    this.getMembersProject();
  }

  getProject() {
    this.projectService.getprojectById(this.id).subscribe((response: any) => {
      this.project = response.data.data;
      console.log('hello' + this.project.title);
      this.form.controls['title'].setValue(this.project.title);
      this.form.controls['description'].setValue(this.project.description);
      this.form.controls['startDate'].setValue(this.project.startDate);
      this.form.controls['endDate'].setValue(this.project.endDate);
      console.log('hello1' + this.project);
    });
  }

  getAttachments() {
    // this.projectService.getAttachmentsByProjectId(this.id).subscribe(data =>{
    //   this.attachments = data;
    //   console.log(this.attachments)
    // })
  }

  setEditingTitle(mode: boolean) {
    this.isEditingTitle = mode;
  }

  cancelUpdateTitle() {
    this.setEditingTitle(false);
    this.form.controls['title'].setValue(this.project.title);
  }

  updateProject() {
    this.setEditingTitle(false);
    console.log(this.project);
  }

  getMembersProject() {
    // this.projectService.getMembersProject(this.id).subscribe(data =>{
    //   this.invitations = data;
    //   console.log(this.invitations)
    // })
  }

  subStrName(user: User) {
    return user.firstName.substring(0, 1) + user.lastName.substring(0, 1);
  }

  fileBrowseHandler(event: any) {
    console.log(event.target.files);
    for (var i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }

    console.log(this.files);
  }

  deleteFile(file: any) {
    for (let i = 0; i < this.files.length; i++) {
      if (this.files[i] === file) {
        this.files.splice(i, 1);
      }
    }
  }
}
