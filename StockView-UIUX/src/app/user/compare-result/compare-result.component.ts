import { Component, OnInit } from "@angular/core";
import { Chart } from "chart.js";
import { StockPriceService } from "src/app/services/stock-price.service";
import { StockPrice } from "src/app/models/stockPrice";

@Component({
    selector: "app-compare-result",
    templateUrl: "./compare-result.component.html",
    styleUrls: ["./compare-result.component.css"]
})
export class CompareResultComponent implements OnInit {
    myChart;
    stockPrices: StockPrice[];
    labels: any[] = [];
    data: any[] = [];
    constructor(private stockPriceService: StockPriceService) {}

    ngOnInit() {
        this.stockPriceService.getAllStockPrices().subscribe(data => {
            this.stockPrices = data;
            for(let stockPrice of this.stockPrices){
                this.labels.push(stockPrice.time);
                this.data.push(stockPrice.currentPrice);
            }
            this.myChart = new Chart("outputChart", {
            type: "bar",
            data: {
                labels: this.labels,
                datasets: [
                    {
                        label: "Test",
                        data: this.data,
                        backgroundColor: "#000555",
                        borderWidth: 1
                    }
                ]
            }
        });
        });

        
    }
}
