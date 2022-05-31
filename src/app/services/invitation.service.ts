import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Invitation, SendedInvitation } from '../models/invitation';

const AUTH_API = environment.baseURL;

@Injectable({
  providedIn: 'root',
})
export class InvitationService {
  constructor(private httpClient: HttpClient) {}

  getAllInvitationByManager(): Observable<Invitation[]> {
    return this.httpClient.get<Invitation[]>(
      AUTH_API + 'api/invitations/invitationByManager'
    );
  }

  getAllInvitationByMember(): Observable<Invitation[]> {
    return this.httpClient.get<Invitation[]>(
      AUTH_API + 'api/invitations/invitationByMember'
    );
  }

  getAllInvitationByProject(projectId: number): Observable<Invitation[]> {
    return this.httpClient.get<Invitation[]>(
      AUTH_API + 'api/invitations/' + projectId
    );
  }

  acceptInvitationByProject(projectId: number): Observable<any> {
    return this.httpClient.put(
      AUTH_API + 'api/invitations/acceptInvitation/' + projectId,
      null
    );
  }
  refuseInvitationByProject(projectId: number): Observable<any> {
    return this.httpClient.put(
      AUTH_API + 'api/invitations/refuseInvitation/' + projectId,
      null
    );
  }
  sendInvitationByProject(sendedInvitation: SendedInvitation): Observable<any> {
    return this.httpClient.post(
      AUTH_API + 'api/invitations/sendInvitation',
      sendedInvitation
    );
  }

  removeInvitationByProject(projectId: number): Observable<any> {
    return this.httpClient.delete(
      AUTH_API + 'api/invitations/removeInvitation/' + projectId
    );
  }
}
