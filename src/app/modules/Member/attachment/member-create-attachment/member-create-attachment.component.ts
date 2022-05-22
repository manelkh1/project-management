import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Attachment } from 'src/app/models/attachement';
import { User } from 'src/app/models/user';
import { AttachementService } from 'src/app/services/attachment.service';
import { ConvertDateService } from 'src/app/services/convert-date.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-member-create-attachment',
  templateUrl: './member-create-attachment.component.html',
  styleUrls: ['./member-create-attachment.component.scss']
})
export class MemberCreateAttachmentComponent implements OnInit {
  attachment: Attachment = new Attachment();
  //objet createAttachment
  /*   createAttachment: CreateAttachment = new CreateAttachment(); */
  form!: FormGroup;
  searchUsers: User[] = [];
  users: User[] = [];
  newDate = new Date();
  files: any[] = [];
  constructor(private attachmentService: AttachementService,
    private userService: UserService,
    private _fb: FormBuilder,
    private convertDateService: ConvertDateService,
    private router: Router) { }

  ngOnInit(): void {
    this.form = this._fb.group({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
    });
  }

  addAttachment() {
    /*  this.attachmentService
      .a(this.attachment)
      .subscribe((data: { id: any }) => {
        this.form.reset();
        // redirect the path from create-attachment to attachment-details
        this.router.navigate(['/default/attachment-details', data.id]);
      }); */
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

}
