import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { User } from '../models/users';

const url = "http://localhost:8765/user-service/login";

@Injectable({
    providedIn: 'root'
})
export class AuthServiceService {
    constructor(private userService: UserService, private http: HttpClient) { }

    authenticate(username: string, password: string) {
        // create a security token
        let authenticationToken = "Basic " + window.btoa(username + ":" + password);

        let headers = new HttpHeaders({
            Authorization: authenticationToken
        });

        return this.http.get(url, { headers }).pipe(
            map((data: User) => {
                sessionStorage.setItem("username", username);
                sessionStorage.setItem("token", authenticationToken);
                sessionStorage.setItem("userType", (data.userType == "ROLE_ADMIN") ? "admin" : "user");
                return data;
            }),
            map(error => {
                return error;
            })
        );
    }

    getAuthenticationToken() {
        if (this.isUserLoggedIn())
            return sessionStorage.getItem("token");
        return null;
    }

    isUserLoggedIn(): boolean {
        let user = sessionStorage.getItem('username');
        if (user == null)
            return false;
        return true;
    }

    logout() {
        sessionStorage.removeItem("username");
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("userType");
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
