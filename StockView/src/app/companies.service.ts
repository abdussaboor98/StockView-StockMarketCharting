import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Company } from './models/company';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompaniesService {

  url = 'http://localhost:5000/companies/';
  constructor(private httpClient: HttpClient) { }

  getAllCompanies() : Observable<Company>{
    return this.httpClient.get<Company>(this.url);
  }

  addCompany(company) : Observable<Company>{
    return this.httpClient.post<Company>(this.url,company);
  }

}
