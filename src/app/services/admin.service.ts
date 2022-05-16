import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../models/admin';
import  {environment } from '../../environments/environment'
const AUTH_API = environment.baseURL;
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient: HttpClient) { }

  getAllAdmins(): Observable<Admin[]> {
    return this.httpClient.get<Admin[]>( AUTH_API + 'api/projects/admins/');
  }

  getAdminById(adminId: number): Observable<Admin[]> {
    return this.httpClient.get<Admin[]>( AUTH_API + 'api/projects/admins/' + adminId);
  }

  createAdmin(admin: Admin): Observable<any> {
    return this.httpClient.post(AUTH_API + 'api/admins' , admin);
  }

  updateAdmin(adminId: number,admin:Admin): Observable<any> {
    return this.httpClient.put(AUTH_API + 'api/admins/' +adminId, admin);
  }

  deleteAdmin(adminId: number): Observable<any> {
    return this.httpClient.delete(AUTH_API + 'api/admins/' + adminId);
  }

}
