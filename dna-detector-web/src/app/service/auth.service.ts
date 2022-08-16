import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

import { environment } from './../../environments/environment';
import { User } from '../entity/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url: string;

  constructor(
    private jwtHelper: JwtHelperService,
    private http: HttpClient,
  ) {
    this.url = environment.apiRest;
  }

  public login(user: User): Observable<any> {
    return this.http.post(this.url.concat("login"), user);
  }

  public saveToken(token: string): void {
    localStorage.setItem('ACCESS_TOKEN', token);
  }
    
  public isAuthenticated(): boolean {
    const token = localStorage.getItem('ACCESS_TOKEN');    
    return !this.jwtHelper.isTokenExpired(token);
  }
    
  public logOut(){  
    localStorage.removeItem('ACCESS_TOKEN');
  }

}
