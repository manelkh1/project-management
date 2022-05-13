import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Attachement } from 'src/app/Model/attachement';
import { CreateProject } from 'src/app/Model/CreateProject';
import { Invitation } from 'src/app/Model/invitation';
import { Project } from 'src/app/Model/project';
import { environment } from 'src/environments/environment';
const AUTH_API = environment.baseURL;

let headers = new HttpHeaders();
headers.set('Content-Type', undefined!);
headers.set('Accept', 'multipart/form-data');
let params = new HttpParams();

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  constructor(private httpClient: HttpClient) {}

  addProject(project: CreateProject, files: any[]): Observable<any> {
    //create a FormData object
    const formData: FormData = new FormData();
    //ajouter des champs
    for (var i = 0; i < files.length; i++) {
      formData.append('files', files[i]);
    } 
    formData.append(
      'project',
      new Blob([JSON.stringify(project)], { type: 'application/json' })
    );

    return this.httpClient.post(AUTH_API + 'api/projects/add', formData, {
      params,
      headers,
    });
  }

  getAllProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(
      AUTH_API + 'api/projects/allProjects'
    );
  }

  getprojectById(projectId: number): Observable<Project> {
    return this.httpClient.get<Project>(
      AUTH_API + 'api/projects/projectById/' + projectId
    );
  }

  getAttachmentsByProjectId(projectId: number): Observable<Attachement[]> {
    return this.httpClient.get<Attachement[]>(
      AUTH_API + 'api/projects/attachments/' + projectId
    );
  }

  getMembersProject(projectId: number): Observable<Invitation[]> {
    return this.httpClient.get<Invitation[]>(
      AUTH_API + 'api/projects/memberProject/' + projectId
    );
  }

  getInvitationsByUser(): Observable<Invitation[]> {
    return this.httpClient.get<Invitation[]>(
      AUTH_API + 'api/projects/invitationsProject'
    );
  }
}
