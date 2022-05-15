import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateProject } from '../models/CreateProject'
import { environment } from 'src/environments/environment';
import { Project } from '../models/project';






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

  
  getAllProjectsByAdmin(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(
      AUTH_API + 'api/projects/projectsByAdmin'
    );
  }

  getAllProjectsByManager(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(
      AUTH_API + 'api/projects/projectsByManager'
    );
  }

  getAllProjectsByMember(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(
      AUTH_API + 'api/projects/projectsByMember/'
    );
  }

  getAllProjectsByBank(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(
      AUTH_API + 'api/projects/projectsByBank/'
    );
  }

  getprojectById(projectId: number): Observable<Project> {
    return this.httpClient.get<Project>(
      AUTH_API + 'api/projects/projects/' + projectId
    );
  }

  createProject(project: Project): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/projects/' , project);
  }

  updateProject(projectId: number,project:Project): Observable<any> {
    return this.httpClient.put(AUTH_API + '/api/projects/' + projectId, project);
  }

  deleteProjectById(projectId: number): Observable<any> {
    return this.httpClient.delete(
      AUTH_API + 'api/projects/projects/' +projectId);
  }



  // getAllProjects(): Observable<Project[]> {
  //   return this.httpClient.get<Project[]>(
  //     AUTH_API + 'api/projects/allProjects'
  //   );
  // }

  // getprojectById(projectId: number): Observable<Project> {
  //   return this.httpClient.get<Project>(
  //     AUTH_API + 'api/projects/projectById/' + projectId
  //   );
  // }

  // getAttachmentsByProjectId(projectId: number): Observable<Attachement[]> {
  //   return this.httpClient.get<Attachement[]>(
  //     AUTH_API + 'api/projects/attachments/' + projectId
  //   );
  // }

  // getMembersProject(projectId: number): Observable<Invitation[]> {
  //   return this.httpClient.get<Invitation[]>(
  //     AUTH_API + 'api/projects/memberProject/' + projectId
  //   );
  // }

  // getInvitationsByUser(): Observable<Invitation[]> {
  //   return this.httpClient.get<Invitation[]>(
  //     AUTH_API + 'api/projects/invitationsProject'
  //   );
  // }
}
