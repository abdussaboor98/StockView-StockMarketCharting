import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompanyStockPriceRequest } from '../models/companyStockPriceRequest';
import { StockPricePerDay } from '../models/stockPricePerDay';

@Injectable({
  providedIn: 'root'
})
export class StockPriceService {

  httpUrl = "http://localhost:8500/stockPrices/"

  constructor(private httpClient:HttpClient) { }

  uploadStocksSheet(formData: FormData): Observable<any>{
    return this.httpClient.post<any>(this.httpUrl+"uploadStocksSheet",formData);
  }

  getCompanyStockPricesBetween(companyCode: string, stockExchange: string, startDate: Date, endDate: Date, periodicity: number): Observable<StockPricePerDay[]> {
    let url = "companyStockPriceBetween/"+companyCode+"/"+stockExchange+"/"+startDate.toISOString().split('T')[0]+"/"+endDate.toISOString().split('T')[0]+"/"+periodicity;
    return this.httpClient.get<StockPricePerDay[]>(this.httpUrl+url);
  }

  getMaxDate(companyCode: string, stockExchange: string): Observable<string>{
    let url = "getMaxDate/"+companyCode+"/"+stockExchange;
    return this.httpClient.get<string>(this.httpUrl+url)
  }

  getMinDate(companyCode: string, stockExchange: string): Observable<string>{
    let url = "getMinDate/"+companyCode+"/"+stockExchange;
    return this.httpClient.get<string>(this.httpUrl+url)
  }
}
