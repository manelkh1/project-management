import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Manager } from '../../Model/manager';
import { Project } from '../../Model/project';

const AUTH_API = environment.baseURL;

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient) { }


   
  getAllCommentsByProject(projectId: number): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>( AUTH_API + '/api/comments/managers/' + projectId);
  }

  createManager(manager: Manager): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/comments' , manager);
  }
}
