import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockPrice } from '../models/stockPrice';

@Injectable({
  providedIn: 'root'
})
export class StockPriceService {

  httpUrl = "http://localhost:8500/stockPrices/"

  constructor(private httpClient:HttpClient) { }

  uploadStocksSheet(formData: FormData): Observable<object>{
    return this.httpClient.post<object>(this.httpUrl+"uploadStocksSheet",formData);
  }

  // getAllStockPrices():Observable<StockPrice[]>{
  //   return this.httpClient.get<StockPrice[]>(this.httpUrl);
  // }
}
