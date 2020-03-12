import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Company } from "../models/company";
import { Observable } from "rxjs";
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: "root"
})
export class CompaniesService {
    url = environment.host + "company-service/companies/";
    constructor(private httpClient: HttpClient) {}

    getAllCompanies(): Observable<Company[]> {
        return this.httpClient.get<Company[]>(this.url);
    }

    getAllCompaniesByPattern(pattern: string): Observable<Company[]> {
        return this.httpClient.get<Company[]>(this.url+"getCompaniesByPattern/"+pattern);
    }

    getCompayById(id: number): Observable<Company> {
        return this.httpClient.get<Company>(this.url + id);
    }

    addCompany(company: Company): Observable<Company> {
        return this.httpClient.post<Company>(this.url+"admin/", company);
    }

    deleteCompany(id: number): Observable<Company> {
        return this.httpClient.delete<Company>(this.url+"admin/" + id);
    }

    deactivateCompany(id: number): Observable<Company> {
        return this.httpClient.put<Company>(this.url+"admin/deactivate/",id);
    }

    activateCompany(id: number): Observable<Company> {
        return this.httpClient.put<Company>(this.url+"admin/activate/",id);
    }

    updateCompany(company: Company): Observable<Company> {
        return this.httpClient.put<Company>(this.url+"admin/", company);
    }

    getCompaniesByStockExchange(stockExchange:string): Observable<Company[]>{
        return this.httpClient.get<Company[]>(this.url+"getCompaniesByStockExchange/"+stockExchange);
    }
}
