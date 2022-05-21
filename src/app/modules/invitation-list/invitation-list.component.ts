import { MemberService } from './../../services/member.service';
import { Member } from './../../models/member';
import { MatSnackBar } from '@angular/material/snack-bar';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { first } from 'rxjs';
import { InvitationService } from './../../services/invitation.service';
import { Component, OnInit } from '@angular/core';
import { Invitation } from '../../models/invitation';
import { Status } from '../../models/status';
import { User } from '../../models/user';
import { ProjectService } from '../../services/project.service';
import { UserService } from '../../services/user.service';
import { Project } from 'src/app/models/project';

@Component({
  selector: 'app-invitation-list',
  templateUrl: './invitation-list.component.html',
  styleUrls: ['./invitation-list.component.scss'],
})
export class InvitationListComponent implements OnInit {
  invitations: any;
  projects: any;
  members: any;
  status: Status[] = [];
  project: Project = new Project();
  form!: FormGroup;
  invitation: Invitation = new Invitation();
  constructor(
    private projectService: ProjectService,
    private userService: UserService,
    private invitationService: InvitationService,
    private memberService: MemberService,
    private _snackBar: MatSnackBar,
    private _fb: FormBuilder
  ) {}

  ngOnInit(): void {
    /// this.getInvitationsByUser();

    this.form = this._fb.group({
      projectId: new FormControl('', [Validators.required]),
      memberId: new FormControl('', [Validators.required]),
    });
    // this.getAllProjectsByManager();
    //this.getAllMembers();
    /*  getAllMembers() { */
    /*  this.memberService
      .getAllMembers()
      .pipe(first())
      .subscribe((members) => {
        //this.loading = false;
        this.members = members;
        console.log(this.members);
      });
    console.log(this.members); */
    /* } */
    this.getAllMembers();
    this.getAllProjectsByManager();
  }

  getAllMembers(): void {
    this.memberService.getAllMembers().subscribe(
      (data: any) => {
        this.members = data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getAllProjectsByManager(): void {
    this.projectService.getAllProjectsByManager().subscribe(
      (data: any) => {
        this.projects = data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  sendInvitationByProject() {
    this.invitation.project = this.form.value.projectId;
    this.invitation.member = this.form.value.memberId;
    console.log(this.invitation.project);
    console.log(this.invitation.project);
    this.invitationService
      .sendInvitationByProject(this.invitation)
      .subscribe((data) => {
        this.form.reset();
        /// this.openSnackBar(data.message, data.type.toLowerCase(), data.type);
      });
  }

  /*   getInvitationsByUser(){
    this.projectService.getInvitationsByUser().subscribe(data =>{
      this.invitations = data;
      console.log(this.invitations)
    })
  } */

  /*  subStrName(user:User){
    return user.firstName.substring(0,1)+user.lastName.substring(0,1);
  }


  acceptInv(invitationId:number){

  }

  refuseInv(invitationId:number){
  } */
}
