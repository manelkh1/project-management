import { MemberService } from '../../../../services/member.service';
import { Member } from '../../../../models/member';
import { MatSnackBar } from '@angular/material/snack-bar';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { first } from 'rxjs';
import { InvitationService } from '../../../../services/invitation.service';
import { Component, OnInit } from '@angular/core';
import { Invitation } from '../../../../models/invitation';
import { Status } from '../../../../models/status';
import { User } from '../../../../models/user';
import { ProjectService } from '../../../../services/project.service';
import { UserService } from '../../../../services/user.service';
import { Project } from 'src/app/models/project';

@Component({
  selector: 'app-member-invitation-list',
  templateUrl: './member-invitation-list.component.html',
  styleUrls: ['./member-invitation-list.component.scss'],
})
export class MemberInvitationListComponent implements OnInit {
  selected: string = 'dumling';
  title: string = '';

  projects: any;
  members: any;
  managers: any;
  receivedInvitations: any;
  status: Status[] = [];
  project: Project = new Project();
  form!: FormGroup;
  invitation: Invitation = new Invitation();
  seleccionados!: any;
  // selected: string = '';
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
    console.log('this ' + this.seleccionados);

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
    // this.getAllInvitationByMember();
    this.getAllInvitationByMember();
    console.log(this.getAllInvitationByMember());
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

  getAllInvitationByMember(): void {
    this.invitationService.getAllInvitationByMember().subscribe(
      (data: any) => {
        this.receivedInvitations = data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  acceptInvitationByProject(idProject: number) {
    console.log(idProject);
    this.invitationService.acceptInvitationByProject(idProject).subscribe(
      (data: any) => {
        this.receivedInvitations = data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  refuseInvitationByProject(idProject: number) {
    console.log(idProject);
    this.invitationService.refuseInvitationByProject(idProject).subscribe(
      (data: any) => {
        this.receivedInvitations = data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
