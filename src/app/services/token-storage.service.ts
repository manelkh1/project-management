

// to manages token and user information
// (username, email, roles) inside Browser’s Session Storage.


import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';


@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }
  
// For Logout, we only need to clear this Session Storage.
  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(jwt: string) {

    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, jwt);
  }

  public getToken() {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser() {
    let user = sessionStorage.getItem(USER_KEY);
    return user;
  }

}
