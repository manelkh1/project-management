import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Manager } from '../models/manager';
import  {environment } from '../../environments/environment'


const AUTH_API = environment.baseURL;



@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  constructor(private httpClient: HttpClient) { }

  getAllManagers(): Observable<Manager[]> {
    return this.httpClient.get<Manager[]>( AUTH_API + '/api/projects/managers/');
  }
    
  getManagerById(managerId: number): Observable<Manager[]> {
    return this.httpClient.get<Manager[]>( AUTH_API + '/api/projects/managers/' + managerId);
  }

  createManager(manager: Manager): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/managers/' , manager);
  }

  updateManager(managerId: number,manager:Manager): Observable<any> {
    return this.httpClient.put(AUTH_API + '/api/managers/' +managerId, manager);
  }

  deleteManager(managerId: number): Observable<any> {
    return this.httpClient.delete(AUTH_API + '/api/managers/' + managerId);
  }
 
}
