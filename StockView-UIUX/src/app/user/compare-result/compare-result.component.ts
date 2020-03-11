import { Component, OnInit } from "@angular/core";
import { StockPriceService } from "src/app/services/stock-price.service";
import { StockPrice } from "src/app/models/stockPrice";
import * as Highcharts from 'highcharts';
import { ActivatedRoute } from '@angular/router';
import { CompareFormData } from 'src/app/models/CompareFormData';
import { StockPricePerDay } from 'src/app/models/stockPricePerDay';

@Component({
    selector: "app-compare-result",
    templateUrl: "./compare-result.component.html",
    styleUrls: ["./compare-result.component.css"]
})
export class CompareResultComponent implements OnInit {
    compareData: CompareFormData;
    singleChart: boolean = false;
    doubleChart: boolean = false;
    constructor(private stockPriceService: StockPriceService, private route: ActivatedRoute) { }

    chartOne = Highcharts;
    chartOneOptions: any;
    chartTwo = Highcharts;
    chartTwoOptions: any;

    getFirstDataComplete: boolean = false;
    getSecondDataComplete: boolean = false;

    ngOnInit() {

        this.compareData = JSON.parse(this.route.snapshot.queryParams.formData);
        console.log(this.compareData);
        let series: any = []
        if (this.compareData.periods.length > 0) {
            this.singleChart = true;
            let categories: any[] = [];
            this.stockPriceService.getCompanyStockPricesBetween(this.compareData.companies[0].companyCode, this.compareData.companies[0].stockExchange, this.compareData.periods[0].fromDate, this.compareData.periods[0].toDate).subscribe(data => {
                let companyOneData: any[] = [];
                data.forEach((stockPrice: StockPricePerDay) => {
                    companyOneData.push(stockPrice.avgPrice)
                    if (!categories.includes(stockPrice.date)) {
                        categories.push(stockPrice.date);
                    }
                })
                let seriesDataMemberOne = {
                    name: this.compareData.companies[0].companyCode + " (" + this.compareData.companies[0].stockExchange + ")",
                    data: companyOneData
                }
                series[0] = seriesDataMemberOne;
                this.getFirstDataComplete = true;
            });
            this.stockPriceService.getCompanyStockPricesBetween(this.compareData.companies[1].companyCode, this.compareData.companies[1].stockExchange, this.compareData.periods[0].fromDate, this.compareData.periods[0].toDate).subscribe(data => {
                let companyTwoData: any[] = [];
                data.forEach((stockPrice: StockPricePerDay) => {
                    companyTwoData.push(stockPrice.avgPrice)
                    if (!categories.includes(stockPrice.date)) {
                        categories.push(stockPrice.date);
                    }
                })
                let seriesDataMemberTwo = {
                    name: this.compareData.companies[1].companyCode + " (" + this.compareData.companies[1].stockExchange + ")",
                    data: companyTwoData
                }
                series[1] = seriesDataMemberTwo;
                this.getSecondDataComplete = true;
            });
            this.chartOneOptions = {
                chart: {
                    type: "column"
                },
                title: {
                    text: "Stock Comparision Chart"
                },
                xAxis: {
                    categories: categories
                },
                yAxis: {
                    title: {
                        text: "Stock Price"
                    }
                },
                series: series
            }
            console.log(this.chartOneOptions);

        }
        if (this.compareData.periods.length > 1) {
            this.doubleChart = true;
        }
    }
}
