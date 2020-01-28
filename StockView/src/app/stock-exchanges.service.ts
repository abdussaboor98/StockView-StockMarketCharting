import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockExchange } from './models/stockExchange';

@Injectable({
  providedIn: 'root'
})
export class StockExchangesService {

  url ="http://localhost:4000/stockexchanges/"
  constructor(private httpClient : HttpClient) { }

  getAllExchanges(): Observable<StockExchange>{
    return this.httpClient.get<StockExchange>(this.url);
  }

  addStockExchange(stockEx : StockExchange):Observable<StockExchange>{
    return this.httpClient.post<StockExchange>(this.url,stockEx);
  }
}
