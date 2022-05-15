import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Invitation } from '../models/invitation';

const AUTH_API = environment.baseURL;

@Injectable({
  providedIn: 'root'
})
export class InvitationService {

  constructor(private httpClient: HttpClient) { }

  getAllInvitationByMember(): Observable<Invitation[]> {
    return this.httpClient.get<Invitation[]>( AUTH_API + '/api/invitations/' );
  }
    
  getAllInvitationByProject(projectId: number): Observable<Invitation[]> {
    return this.httpClient.get<Invitation[]>( AUTH_API + '/api/invitations/' + projectId);
  }

  acceptInvitationByProject(projectId: number): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/invitations/acceptInvitation/' , + projectId);
  }
  refuseInvitationByProject(projectId: number): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/invitations/refuseInvitation/' , + projectId);
  }
  sendInvitationByProject(invitation: Invitation ): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/invitations/sendInvitation/' , invitation );
  }

  removeInvitationByProject(projectId:number): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/invitations/removeInvitation/'  ,+projectId);
  }
}
