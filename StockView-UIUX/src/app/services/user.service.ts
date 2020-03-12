import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../models/users";
import { environment } from 'src/environments/environment';
import { PasswordChange } from '../models/passwordChange';

@Injectable({
    providedIn: "root"
})
export class UserService {
    url = environment.host+"user-service/users/";
    constructor(private httpClient: HttpClient) {}

    getAllUsers(): Observable<User[]> {
        return this.httpClient.get<User[]>(this.url);
    }

    getUserById(id: number): Observable<User> {
        return this.httpClient.get<User>(this.url + id);
    }

    emailExists(email: string): Observable<boolean> {
        return this.httpClient.get<boolean>(this.url + "open/emailExists/" + email);
    }

    usernameExists(username: string): Observable<boolean> {
        return this.httpClient.get<boolean>(this.url + "open/usernameExists/" + username);
    }

    isUserActive(username: string): Observable<boolean> {
        return this.httpClient.get<boolean>(this.url + "open/isUserActive/" + username);
    }
 
    getUserByUsernameAndPassword(
        username: string,
        password: string
    ): Observable<User> {
        return this.httpClient.get<User>(this.url + "findByUsernameAndPassword/" + username+"/"+password);
    }
    getUserByUsername(username: string): Observable<User> {
        return this.httpClient.get<User>(this.url + "findByUsername/" + username);
    }

    registerUser(user: User): Observable<User> {
        return this.httpClient.post<User>(this.url+"open/addUser", user);
    }

    deleteUser(id: number): Observable<User> {
        return this.httpClient.delete<User>(this.url + id);
    }

    updateUser(user: User): Observable<User> {
        return this.httpClient.put<User>(this.url, user);
    }

    updatePassword(request: PasswordChange): Observable<boolean> {
        return this.httpClient.put<boolean>(this.url+"updatePassword", request);
    }

    activateUser(email: string): Observable<boolean> {
        return this.httpClient.put<boolean>(this.url + "open/activate", email);
    }
}
