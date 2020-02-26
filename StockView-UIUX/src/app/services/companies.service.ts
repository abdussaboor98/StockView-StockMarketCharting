import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Company } from "../models/company";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root"
})
export class CompaniesService {
    url = "http://localhost:8080/companies/";
    constructor(private httpClient: HttpClient) {}

    getAllCompanies(): Observable<Company[]> {
        return this.httpClient.get<Company[]>(this.url);
    }

    getCompayById(id: number): Observable<Company> {
        return this.httpClient.get<Company>(this.url + id);
    }

    addCompany(company: Company): Observable<Company> {
        return this.httpClient.post<Company>(this.url, company);
    }

    deleteCompany(id: number): Observable<Company> {
        return this.httpClient.delete<Company>(this.url + id);
    }

    updateCompany(company: Company): Observable<Company> {
        return this.httpClient.put<Company>(this.url + company.id, company);
    }
}
