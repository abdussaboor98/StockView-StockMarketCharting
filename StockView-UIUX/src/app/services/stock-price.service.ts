import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompanyStockPriceRequest } from '../models/companyStockPriceRequest';
import { StockPriceData } from '../models/stockPriceData';
import { UploadSummary } from '../models/uploadSummary';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StockPriceService {

  httpUrl = environment.host + "stock-price-service/stockPrices/"

  constructor(private httpClient:HttpClient) { }

  uploadStocksSheet(formData: FormData): Observable<UploadSummary>{
    return this.httpClient.post<any>(this.httpUrl + "admin/uploadStocksSheet",formData);
  }

  getCompanyStockPricesBetween(companyCode: string, stockExchange: string, startDate: Date, endDate: Date,periodicity: string): Observable<StockPriceData[]> {
    let url = "companyStockPriceBetween/"+companyCode+"/"+stockExchange+"/"+startDate+"/"+endDate+"/"+periodicity;
    return this.httpClient.get<StockPriceData[]>(this.httpUrl+url);
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
