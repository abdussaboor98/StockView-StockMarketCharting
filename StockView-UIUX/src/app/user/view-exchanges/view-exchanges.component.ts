import { Component, OnInit } from "@angular/core";
import { StockExchangesService } from "src/app/services/stock-exchanges.service";
import { StockExchange } from "src/app/models/stockExchange";

@Component({
    selector: "app-view-exchanges",
    templateUrl: "./view-exchanges.component.html",
    styleUrls: ["./view-exchanges.component.css"]
})
export class ViewExchangesComponent implements OnInit {
    stockExchanges: StockExchange[];
    constructor(private stockExService: StockExchangesService) {}

    ngOnInit() {
        this.stockExService.getAllExchanges().subscribe(data => {
            this.stockExchanges = data;
        });
    }
}
