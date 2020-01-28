import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './models/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url="localhost:3000/users/"
  constructor(private httpClient : HttpClient)  { }

  getAllUsers() : Observable<User[]> {
    return this.httpClient.get<User[]>(this.url);
  }
  
  registerUser(user : User): Observable<User>{
    return this.httpClient.post<User>(this.url,user);
  }
}
