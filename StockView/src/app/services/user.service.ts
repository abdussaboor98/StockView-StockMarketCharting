import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = "http://localhost:3000/users/"
  constructor(private httpClient: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.url);
  }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(this.url + id);
  }

  registerUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.url, user);
  }

  deleteUser(id: number): Observable<User> {
    return this.httpClient.delete<User>(this.url + id);
  }

  updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(this.url + user.id,user);
  }
}
