import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Manager } from '../models/manager';
import { Project } from '../models/project';
import { environment } from '../../environments/environment';
import { Comment } from './../models/comment';
const AUTH_API = environment.baseURL;

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  constructor(private httpClient: HttpClient) {}

  getAllCommentsByProject(projectId: number): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(
      AUTH_API + 'api/comments/' + projectId
    );
  }

  createComment(comment: Comment): Observable<any> {
    return this.httpClient.post(AUTH_API + 'api/comments', comment);
  }
}
