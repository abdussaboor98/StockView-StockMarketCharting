import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { IPO } from "../models/ipo";

@Injectable({
    providedIn: "root"
})
export class IposService {
    url = "http://localhost:8765/initial-public-offering-service/ipos/";
    constructor(private httpClient: HttpClient) {}

    getAllIPOs(): Observable<IPO[]> {
        return this.httpClient.get<IPO[]>(this.url);
    }

    getIPOById(id: number): Observable<IPO> {
        return this.httpClient.get<IPO>(this.url + id);
    }

    addNewIPO(ipo: IPO): Observable<IPO> {
        return this.httpClient.post<IPO>(this.url+"admin/", ipo);
    }

    updateIPO(ipo: IPO): Observable<IPO> {
        return this.httpClient.put<IPO>(this.url+"admin/", ipo);
    }

    deleteIPO(id: number): Observable<IPO> {
        return this.httpClient.delete<IPO>(this.url+"admin/" + id);
    }
}
