import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../models/users";

@Injectable({
    providedIn: "root"
})
export class UserService {
    url = "http://localhost:3000/users/";
    constructor(private httpClient: HttpClient) {}

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
        return this.httpClient.put<User>(this.url + user.id, user);
    }

    isAdmin(username: string): boolean {
        if (
            localStorage.getItem("userType") == "admin" ||
            sessionStorage.getItem("userType") == "admin"
        ) {
            return true;
        } else return false;
    }

    getUserType(username: string): string {
        let type: string;
        this.getAllUsers().subscribe(data => {
            for (let u of data) {
                if (u.userame == username) {
                    if (u.isAdmin) {
                        type = "admin";
                    }
                    type = "user";
                    break;
                }
            }
        });
        return type;
    }

    validateUser(username: string, password: string): boolean {
        let users: User[];
        this.getAllUsers().subscribe(data => {
            users = data;
        });
        for (let u of users) {
            if (u.userame === username && u.password == password) {
                return true;
            }
        }
        return false;
    }
}
