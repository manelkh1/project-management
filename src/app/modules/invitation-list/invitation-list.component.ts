import { Component, OnInit } from '@angular/core';
import { Invitation } from 'src/app/Model/invitation';
import { Status } from 'src/app/Model/status';
import { User } from 'src/app/Model/user';
import { ProjectService } from 'src/app/services/project-service/project.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-invitation-list',
  templateUrl: './invitation-list.component.html',
  styleUrls: ['./invitation-list.component.scss']
})
export class InvitationListComponent implements OnInit {
  invitations:Invitation[]=[];
status: Status[]=[];
  constructor(private projectService:ProjectService ,
    private userService: UserService) { }

  ngOnInit(): void {
    this.getInvitationsByUser();
  }

  getInvitationsByUser(){
    this.projectService.getInvitationsByUser().subscribe(data =>{
      this.invitations = data;
      console.log(this.invitations)
    })
  }

  subStrName(user:User){
    return user.firstName.substring(0,1)+user.lastName.substring(0,1);
  }


  acceptInv(invitationId:number){
console.log("status.description");
  }

  refuseInv(invitationId:number){
  }

}
