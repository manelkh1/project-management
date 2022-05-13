import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const AUTH_API = environment.baseURL;

let headers = { 'content-type': 'application/json' };
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(user: any): Observable<any> {
    return this.http.post(AUTH_API + 'login',user, httpOptions);
  }

}

//Communicating with backend services using HTTP

// The HTTP client service offers the following major features.

// typed response objects
// error handling
// Testability features
// Request and response interception