import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  private apiUrl = 'http://localhost:8080/api/users';
  private authUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  register(user: any) {
    return this.http.post(this.apiUrl, user);
  }

  login(user: any) {
    return this.http.post(
      this.authUrl + '/login',
      user
    );
  }
}
