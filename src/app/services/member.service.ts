import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Member } from '../models/member';
import  {environment } from '../../environments/environment'
const AUTH_API = environment.baseURL;
@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private httpClient: HttpClient) { }
  getAllMembers(): Observable<Member[]> {
    return this.httpClient.get<Member[]>( AUTH_API + 'api/members');
  }

  getMemberById(memberId: number): Observable<Member[]> {
    return this.httpClient.get<Member[]>( AUTH_API + 'api/members' + memberId);
  }

  getAllMembersByProject(memberId: number): Observable<Member[]> {
    return this.httpClient.get<Member[]>( AUTH_API + 'api/members/projects/' + memberId);
  }

  createMember(member: Member): Observable<any> {
    return this.httpClient.post(AUTH_API + 'api/members' , member);
  }

  updateMember(memberId: number,member:Member): Observable<any> {
    return this.httpClient.put(AUTH_API + 'api/members' + memberId, member);
  }

  deleteMember(memberId: number): Observable<any> {
    return this.httpClient.delete(AUTH_API + 'api/members' + memberId);
  }
}
