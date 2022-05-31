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
import { Invitation, SendedInvitation } from '../../../../models/invitation';
import { Status } from '../../../../models/status';
import { User } from '../../../../models/user';
import { ProjectService } from '../../../../services/project.service';
import { UserService } from '../../../../services/user.service';
import { Project } from 'src/app/models/project';
import { Manager } from 'src/app/models/manager';

@Component({
  selector: 'app-send-invitation',
  templateUrl: './send-invitation.component.html',
  styleUrls: ['./send-invitation.component.scss'],
})
export class SendInvitationComponent implements OnInit {
  selected: string = 'dumling';
  title: string = '';
  foods = [
    {
      id: 1,
      date: 'Help Deak',
      status: Status.ACCEPTED,
      manager: new Manager(),
      member: new Member(),
      project: new Project(),
    },
    {
      id: 2,
      date: 'Help Deak',
      status: Status.ACCEPTED,
      manager: new Manager(),
      member: new Member(),
      project: new Project(),
    },
    {
      id: 3,
      date: 'Help Deak',
      status: Status.ACCEPTED,
      manager: new Manager(),
      member: new Member(),
      project: new Project(),
    },
  ];
  projects: any;
  members: any;
  managers: any;
  sendedInvitations: any;
  status: Status[] = [];
  project: Project = new Project();
  form!: FormGroup;
  sendedInvitation: SendedInvitation = new SendedInvitation();
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
    this.getAllInvitationByManager();
    console.log(this.getAllInvitationByManager());
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

  getAllInvitationByManager(): void {
    this.invitationService.getAllInvitationByManager().subscribe(
      (data: any) => {
        this.sendedInvitations = data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  sendInvitationByProject() {
    // console.log('this ' + this.seleccionados.user);
    this.sendedInvitation.projectId = this.form.value.projectId;
    this.sendedInvitation.memberId = this.form.value.memberId;

    /*   console.log(this.invitation.project);
    console.log(this.invitation.member);
    console.log(this.form.value.projectId);
    console.log(this.form.value.memberId); */
    /*   this.members.filter((element) => {
      element.user.lastName;
    }); */

    console.log('  invitation ' + this.sendedInvitation);
    //   console.log(this.invitation.project);
    //  console.log(this.invitation.project);
    this.invitationService
      .sendInvitationByProject(this.sendedInvitation)
      .subscribe((data) => {
        this.form.reset();
        /// this.openSnackBar(data.message, data.type.toLowerCase(), data.type);
      });
  }

  getMember(event: any) {
    console.log(event);
    // this.invitation.member = eve
  }
  removeInvitationByProject(idProject: number) {
    console.log(idProject);
    this.invitationService.removeInvitationByProject(idProject).subscribe(
      (data: any) => {
        this.sendedInvitations = data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
