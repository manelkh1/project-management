import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from 'src/app/Model/project';
import { Role } from 'src/app/Model/role';
import { User } from 'src/app/Model/user';
import { environment } from 'src/environments/environment';

const AUTH_API = environment.baseURL;

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  getAllRole(): Observable<Role[]> {
    return this.httpClient.get<Role[]>(AUTH_API + 'api/users/allRole');
  }

  addUser(user: User, roleId: number): Observable<any> {
    return this.httpClient.post(AUTH_API + 'api/users/add/' + roleId, user);
  }

  // find user by keyword inserted in the research
  getMembers(keyword: string): Observable<User[]> {
    return this.httpClient.get<User[]>(
      AUTH_API + 'api/users/findMember/' + keyword
    );
  }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(AUTH_API + 'api/users/allUsers');
  }

  getUserById(userId: number): Observable<User> {
    return this.httpClient.get<User>(AUTH_API + 'api/users/userById/' + userId);
  }

  getProjectByUserId(userId: number): Observable<Project[]> {
    return this.httpClient.get<Project[]>(
      AUTH_API + 'api/users/projects/' + userId
    );
  }
}
