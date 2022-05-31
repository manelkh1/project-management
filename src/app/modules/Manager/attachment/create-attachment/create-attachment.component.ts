import { UserService } from '../../../../services/user.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';

import { Attachment } from '../../../../models/attachement';
import { User } from '../../../../models/user';
import { ConvertDateService } from '../../../../services/convert-date.service';
import { AttachementService } from '../../../../services/attachment.service';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
@Component({
  selector: 'app-create-attachment',
  templateUrl: './create-attachment.component.html',
  styleUrls: ['./create-attachment.component.scss'],
})
export class ManagerCreateAttachmentComponent implements OnInit {
  attachment: Attachment = new Attachment();
  //objet createAttachment
  /*   createAttachment: CreateAttachment = new CreateAttachment(); */
  form!: FormGroup;
  searchUsers: User[] = [];
  users: User[] = [];
  newDate = new Date();
  files: any[] = [];

  //////////////////////4

  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;

  ////////////////////////
  constructor(
    private attachmentService: AttachementService,
    private userService: UserService,
    private _fb: FormBuilder,
    private convertDateService: ConvertDateService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this._fb.group({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
    });
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  addAttachment() {
    this.files.forEach((file) => {
      const formData: FormData = new FormData();
      console.log(file);
      formData.append('file', file);
      // await this.attachmentService.uploadFile(file).toPromise();

      this.attachmentService.uploadFile(file).subscribe();

      /* this.attachmentService
      .addAttachment())
      .subscribe((data: { id: any }) => {
        this.form.reset();
        // redirect the path from create-attachment to attachment-details
        this.router.navigate(['/default/attachment-details', data.id]);
      }); */
    });
  }

  //find user by the keyword inserted
  findMember(keyword: any) {
    //if there is a written value
    if (keyword.target.value.length > 1) {
      /*  this.userService.getMembers(keyword.target.value.toLowerCase()).subscribe((data: User[]) =>{

        this.searchUsers = data;

        console.log(this.searchUsers)
      }); */
    } else {
      this.searchUsers = [];
    }
  }

  //add the selected user to the users table
  addUser(user: User) {
    const toSelect = this.users.find((c) => c.id === user.id);
    if (toSelect == null) {
      this.users.push(user);
    }
  }

  //************************************** add multiple files ***************/
  fileBrowseHandler(event: any) {
    for (var i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }
  }

  //**************splice is to remove the files from the multiple files******** */
  deleteFile(file: any) {
    for (let i = 0; i < this.files.length; i++) {
      if (this.files[i] === file) {
        this.files.splice(i, 1);
      }
    }
  }

  /////////////////////////////////////
  upload(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.attachmentService.uploadFile(this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round((100 * event.loaded) / event.total);
            } /* else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.uploadService.getFiles();
            } */
          },
          (err: any) => {
            console.log(err);
            this.progress = 0;
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
            this.currentFile = undefined;
          }
        );
      }
      this.selectedFiles = undefined;
    }
  }
}
