import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';;
import { User } from '../models/user';
import { environment } from 'src/environments/environment';

const AUTH_API = environment.baseURL;

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}
  addUser(user: User): Observable<any> {
    return this.httpClient.post(AUTH_API + 'api/users/addUser' , user);
  }



  // find user by keyword inserted in the research
  // getMembers(keyword: string): Observable<User[]> {
  //   return this.httpClient.get<User[]>(
  //     AUTH_API + 'api/users/findMember/' + keyword
  //   );
  // }

  // getAllUsers(): Observable<User[]> {
  //   return this.httpClient.get<User[]>(AUTH_API + 'api/users/allUsers');
  // }

  // getUserById(userId: number): Observable<User> {
  //   return this.httpClient.get<User>(AUTH_API + 'api/users/userById/' + userId);
  // }

  // getProjectByUserId(userId: number): Observable<Project[]> {
  //   return this.httpClient.get<Project[]>(
  //     AUTH_API + 'api/users/projects/' + userId
  //   );
  // }
  // getAllRole(): Observable<Role[]> {
  //   return this.httpClient.get<Role[]>(AUTH_API + 'api/users/allRole');
  // }
}
