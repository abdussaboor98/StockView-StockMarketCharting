import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompanyStockPriceRequest } from '../models/companyStockPriceRequest';
import { StockPricePerDay } from '../models/stockPricePerDay';

@Injectable({
  providedIn: 'root'
})
export class StockPriceService {

  httpUrl = "http://localhost:8765/stock-price-service/stockPrices/"

  constructor(private httpClient:HttpClient) { }

  uploadStocksSheet(formData: FormData): Observable<any>{
    return this.httpClient.post<any>(this.httpUrl+"uploadStocksSheet",formData);
  }

  getCompanyStockPricesBetween(companyCode: string, stockExchange: string, startDate: Date, endDate: Date): Observable<StockPricePerDay[]> {
    let url = "companyStockPriceBetween/"+companyCode+"/"+stockExchange+"/"+startDate+"/"+endDate+"/"+(1);
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
