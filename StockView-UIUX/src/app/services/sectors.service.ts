import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Sector } from "../models/sector";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: "root"
})
export class SectorsService {
    url = "http://localhost:8080/sectors/";
    constructor(private httpClient: HttpClient) {}

    getAllSectors(): Observable<Sector[]> {
        return this.httpClient.get<Sector[]>(this.url);
    }

    getSectorById(id: number): Observable<Sector> {
        return this.httpClient.get<Sector>(this.url + id);
    }

    addNewSector(sector: Sector): Observable<Sector> {
        return this.httpClient.post<Sector>(this.url, sector);
    }

    updateSector(sector: Sector): Observable<Sector> {
        return this.httpClient.put<Sector>(this.url, sector);
    }

    deleteSector(id: number): Observable<Sector> {
        return this.httpClient.delete<Sector>(this.url + id);
    }
}
