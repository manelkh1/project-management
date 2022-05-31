import { InvitationService } from './../../../../services/invitation.service';
import { AttachementService } from './../../../../services/attachment.service';
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
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentService } from 'src/app/services/comment.service';
import { Comment } from './../../../../models/comment';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.scss'],
})
export class ProjectDetailsComponent implements OnInit {
  id!: number;
  project: Project = new Project();
  newProject: Project = new Project();
  comment: Comment = new Comment();
  isEditingTitle = false;
  form!: FormGroup;
  attachement: Attachment[] = [];
  attachments: any;
  invitations: any;
  comments: any;
  files: any[] = [];
  commentForm!: FormGroup;
  newForm!: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private _fb: FormBuilder,
    private projectService: ProjectService,
    private attachementService: AttachementService,
    private invitationService: InvitationService,
    private commentService: CommentService,
    private https: HttpClient
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    console.log(this.id);

    this.form = this._fb.group({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
      newTitle: new FormControl('', [Validators.required]),
    });

    this.newForm = this._fb.group({
      newTitle: new FormControl('', [Validators.required]),
    });
    this.commentForm = this._fb.group({
      message: new FormControl('', [Validators.required]),
    });
    this.getProject();
    this.getAttachments();
    this.getMembersProject();
    this.getAllCommentsByProject();
    this.getAttachmentByProjectAndManager();
    this.getAllInvitationByProject();
  }

  getProject() {
    this.projectService.getprojectById(this.id).subscribe((response: any) => {
      this.project = response.data;
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

  getAttachmentByProjectAndManager() {
    this.attachementService
      .getAttachmentByProjectAndManager(this.id)
      .subscribe((data: any) => {
        this.attachments = data.data;
        //  this.companies = data.data;
        console.log(this.attachments);
      });
  }

  getAllInvitationByProject() {
    this.invitationService
      .getAllInvitationByProject(this.id)
      .subscribe((data: any) => {
        this.invitations = data.data;
        //  this.companies = data.data;
        console.log(this.invitations);
      });
  }

  setEditingTitle(mode: boolean) {
    this.isEditingTitle = mode;
  }

  cancelUpdateTitle() {
    this.setEditingTitle(false);
    this.form.controls['title'].setValue(this.project.title);
  }

  /*   updateProject() {
    this.setEditingTitle(false);
    console.log(this.project);
  } */

  getMembersProject() {
    // this.projectService.getMembersProject(this.id).subscribe(data =>{
    //   this.invitations = data;
    //   console.log(this.invitations)
    // })
  }

  updateProject() {
    this.newProject.title = this.newForm.value.newTitle;
    console.log(this.newForm.value.newTitle);
    this.projectService
      .updateProject(this.id, this.project)
      .subscribe((data: { id: any }) => {
        this.form.reset();
        // redirect the path from create-project to project-details
        /*         this.router.navigate(['/manager/project-details/:id', data.id]); */
      });
  }

  sendProject() {
    this.newProject.title = this.newForm.value.newTitle;
    console.log(this.newForm.value.newTitle);
    this.projectService.sendProject(this.id).subscribe((data: { id: any }) => {
      this.form.reset();
      // redirect the path from create-project to project-details
      /*         this.router.navigate(['/manager/project-details/:id', data.id]); */
    });
  }

  /*   uploadFile() {
    this.attachementService.uploadFile(this.attachement, this.project)
  } */
  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const data: FormData = new FormData();
    data.append('file', file);
    const newRequest = new HttpRequest(
      'POST',
      'http://localhost:8080/savefile',
      data,
      {
        reportProgress: true,
        responseType: 'text',
      }
    );
    return this.https.request(newRequest);
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

  getAllCommentsByProject() {
    this.commentService
      .getAllCommentsByProject(this.id)
      .subscribe((response: any) => {
        console.log('hellocomment' + response);
        console.log('hellocomment' + response.data);
        console.log('hellocomment');
        this.comments = response.data;
        console.log('commentsss ' + this.comments);
        this.form.controls['message'].setValue(this.comment.message);
        this.form.controls['sender'].setValue(this.comment.sender);
        this.form.controls['manager'].setValue(this.comment.manager);
        this.form.controls['member'].setValue(this.comment.member);
      });
  }

  createComment() {
    this.comment.message = this.commentForm.value.message;
    this.comment.idProject = this.id;
    this.commentService
      .createComment(this.comment)
      .subscribe((data: { id: any }) => {
        this.form.reset();
        // redirect the path from create-project to project-details
      });
  }
}
