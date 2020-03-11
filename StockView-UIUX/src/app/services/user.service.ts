import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../models/users";

@Injectable({
    providedIn: "root"
})
export class UserService {
    url = "http://localhost:8765/user-service/users/";
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
        return this.httpClient.post<User>(this.url, user);
    }

    deleteUser(id: number): Observable<User> {
        return this.httpClient.delete<User>(this.url + id);
    }

    updateUser(user: User): Observable<User> {
        return this.httpClient.put<User>(this.url, user);
    }

    activateUser(email: string): Observable<boolean> {
        return this.httpClient.put<boolean>(this.url + "activate", email);
    }

    isAdmin(): boolean {
        if (
            localStorage.getItem("userType") == "admin" ||
            sessionStorage.getItem("userType") == "admin"
        ) {
            return true;
        } else {
            return false;
        }
    }
    isUser(): boolean {
        if (
            localStorage.getItem("userType") == "user" ||
            sessionStorage.getItem("userType") == "user"
        ) {
            return true;
        } else {
            return false;
        }
    }
}
