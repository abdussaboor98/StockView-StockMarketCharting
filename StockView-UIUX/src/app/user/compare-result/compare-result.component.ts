import { Component, OnInit } from "@angular/core";
import { Chart } from "chart.js";
import { StockPriceService } from "src/app/services/stock-price.service";
import { StockPrice } from "src/app/models/stockPrice";
import * as Highcharts from 'highcharts';

@Component({
    selector: "app-compare-result",
    templateUrl: "./compare-result.component.html",
    styleUrls: ["./compare-result.component.css"]
})
export class CompareResultComponent implements OnInit {
    stockPrices: StockPrice[];
    highcharts = Highcharts;
    data: any[] = [];
    chartOptions: any;
    constructor(private stockPriceService: StockPriceService) { }

    ngOnInit() {
        this.data = [{
            name: 'ItSolutionStuff.com',
            data: [500, 700, 555, 444, 777, 877, 944, 567, 666, 789, 456, 654]
        }, {
            name: 'Nicesnippets.com',
            data: [677, 455, 677, 877, 455, 778, 888, 567, 785, 488, 567, 654]
        }];

        
        this.chartOptions = {
            chart: {
                type: "column"
            },
            title: {
                text: "Monthly Site Visitor"
            },
            xAxis: {
                categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
            },
            yAxis: {
                title: {
                    text: "Visitors"
                }
            },
            series: this.data
        };
    }
}
