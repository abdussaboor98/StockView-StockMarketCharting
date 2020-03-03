import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { StockExchange } from "../models/stockExchange";

@Injectable({
    providedIn: "root"
})
export class StockExchangesService {
    url = "http://localhost:8400/stockExchanges/";
    constructor(private httpClient: HttpClient) {}

    getAllExchanges(): Observable<StockExchange[]> {
        return this.httpClient.get<StockExchange[]>(this.url);
    }

    getExchangeById(id: number): Observable<StockExchange> {
        return this.httpClient.get<StockExchange>(this.url + id);
    }

    addStockExchange(stockEx: StockExchange): Observable<StockExchange> {
        return this.httpClient.post<StockExchange>(this.url, stockEx);
    }

    updateStockExchange(stockEx: StockExchange): Observable<StockExchange> {
        return this.httpClient.put<StockExchange>(this.url,stockEx);
    }

    deleteStockExchange(id: number): Observable<StockExchange> {
        return this.httpClient.delete<StockExchange>(this.url + id);
    }
}
