import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateProject } from '../models/CreateProject';
import { Project } from '../models/project';
import { environment } from '../../environments/environment';
import { Attachment } from '../models/attachement';

let headers = new HttpHeaders();
headers.set('Content-Type', undefined!);
headers.set('Accept', 'multipart/form-data');
let params = new HttpParams();

const AUTH_API = environment.baseURL;

@Injectable({
  providedIn: 'root',
})
export class AttachementService {
  constructor(private httpClient: HttpClient) {}

  getAllAttachments(): Observable<any> {
    return this.httpClient.get<Attachment[]>(AUTH_API + 'api/attachments');
  }

  getAttachmentByProjectAndManager(projectId: number): Observable<Attachment> {
    return this.httpClient.get<Attachment>(
      AUTH_API + 'api/attachments/getAttachmentByProjectAndManager/' + projectId
    );
  }

  getAttachmentByProjectAndMember(projectId: number): Observable<Attachment> {
    return this.httpClient.get<Attachment>(
      AUTH_API + 'api/attachments/getAttachmentByProjectAndMember/' + projectId
    );
  }

  getAttachmentById(attachmentId: number): Observable<Attachment> {
    return this.httpClient.get<Attachment>(
      AUTH_API + 'api/attachments/' + attachmentId
    );
  }

  findByMember(): Observable<any> {
    return this.httpClient.get<Attachment[]>(
      AUTH_API + 'api/attachments/findByMember'
    );
  }

  findByManager(): Observable<any> {
    return this.httpClient.get<Attachment[]>(
      AUTH_API + 'api/attachments/findByManager'
    );
  }

  uploadFile(attachment: File): Observable<any> {
    return this.httpClient.post(
      AUTH_API + 'api/attachments/uploadFile/12',
      attachment
    );
  }

  uploadMultipleFiles(attachment: File): Observable<any> {
    return this.httpClient.post(
      AUTH_API + 'api/attachments/uploadMultipleFiles/',
      attachment
    );
  }

  downloadFile(attachment: Attachment): Observable<any> {
    return this.httpClient.post(
      AUTH_API + '/api/attachments/downloadFile/',
      attachment
    );
  }

  getAllAttachmentsByAdmin(): Observable<Attachment[]> {
    return this.httpClient.get<Attachment[]>(
      AUTH_API + 'api/attachments/attachmentsByAdmin'
    );
  }

  getAllAttachmentsByManager(): Observable<Attachment[]> {
    return this.httpClient.get<Attachment[]>(
      AUTH_API + 'api/attachments/attachmentsByManager'
    );
  }

  getAllAttachmentsByMember(): Observable<Attachment[]> {
    return this.httpClient.get<Attachment[]>(
      AUTH_API + 'api/attachments/attachmentsByMember'
    );
  }

  getAllAttachmentsByBank(): Observable<Attachment[]> {
    return this.httpClient.get<Attachment[]>(
      AUTH_API + 'api/attachments/attachmentsByBank'
    );
  }

  getattachmentById(attachmentId: number): Observable<Attachment> {
    return this.httpClient.get<Attachment>(
      AUTH_API + 'api/attachments/' + attachmentId
    );
  }

  createAttachment(attachment: Attachment): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/attachments/', attachment);
  }

  updateAttachment(
    attachmentId: number,
    attachment: Attachment
  ): Observable<any> {
    return this.httpClient.put(
      AUTH_API + '/api/attachments/' + attachmentId,
      attachment
    );
  }

  deleteAttachmentById(attachmentId: number): Observable<any> {
    return this.httpClient.delete(
      AUTH_API + 'api/attachments/attachments/' + attachmentId
    );
  }
  
}
