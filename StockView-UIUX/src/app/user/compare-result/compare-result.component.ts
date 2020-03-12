import { Component, OnInit } from "@angular/core";
import { StockPriceService } from "src/app/services/stock-price.service";
import { StockPrice } from "src/app/models/stockPrice";
import * as Highcharts from 'highcharts';
import { ActivatedRoute } from '@angular/router';
import { CompareFormData } from 'src/app/models/CompareFormData';
import { StockPriceData } from 'src/app/models/stockPriceData';

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
        let series: any = []
        if (this.compareData.periods.length == 1) {
            this.singleChart = true;
            let categories: any[] = [];
            this.stockPriceService.getCompanyStockPricesBetween(this.compareData.companies[0].companyCode, this.compareData.companies[0].stockExchange, this.compareData.periods[0].fromDate, this.compareData.periods[0].toDate, this.compareData.periodicity).subscribe(data => {
                let companyOneData: any[] = [];
                data.forEach((stockPrice: StockPriceData) => {
                    categories.push(stockPrice.dataPoint);
                    companyOneData.push(stockPrice.dataValue)
                })
                let seriesDataMemberOne = {
                    name: this.compareData.companies[0].companyCode + " (" + this.compareData.companies[0].stockExchange + ")",
                    data: companyOneData
                }
                series[0] = seriesDataMemberOne;
                setTimeout(() => {
                    this.getFirstDataComplete = true;
                }, 2000)
                this.stockPriceService.getCompanyStockPricesBetween(this.compareData.companies[1].companyCode, this.compareData.companies[1].stockExchange, this.compareData.periods[0].fromDate, this.compareData.periods[0].toDate, this.compareData.periodicity).subscribe(data => {
                    let companyTwoData: any[] = [];
                    data.forEach((stockPrice: StockPriceData) => {
                        if (categories.includes(stockPrice.dataPoint)) {
                            companyTwoData.push(stockPrice.dataValue)
                        }
                    })
                    let seriesDataMemberTwo = {
                        name: this.compareData.companies[1].companyCode + " (" + this.compareData.companies[1].stockExchange + ")",
                        data: companyTwoData
                    }
                    series[1] = seriesDataMemberTwo;
                    setTimeout(() => {
                        this.getSecondDataComplete = true;
                    }, 2000)

                });
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

        }
        if (this.compareData.periods.length > 1) {
            this.doubleChart = true;
            this.singleChart = true;
            this.stockPriceService.getCompanyStockPricesBetween(this.compareData.companies[0].companyCode, this.compareData.companies[0].stockExchange, this.compareData.periods[0].fromDate, this.compareData.periods[0].toDate, this.compareData.periodicity).subscribe(data => {
                let companyData: any[] = [];
                let categories: any[] = [];
                data.forEach((stockPrice: StockPriceData) => {
                    categories.push(stockPrice.dataPoint);
                    companyData.push(stockPrice.dataValue);
                })
                let series = [{
                    name: this.compareData.companies[0].companyCode + " (" + this.compareData.companies[0].stockExchange + ")",
                    data: companyData
                }]
                this.getFirstDataComplete = true;
                this.chartOneOptions = {
                    chart: {
                        type: "column"
                    },
                    title: {
                        text: "Company 1"
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
            });

            this.stockPriceService.getCompanyStockPricesBetween(this.compareData.companies[0].companyCode, this.compareData.companies[0].stockExchange, this.compareData.periods[1].fromDate, this.compareData.periods[1].toDate, this.compareData.periodicity).subscribe(data => {
                let companyData: any[] = [];
                let categories: any[] = [];
                data.forEach((stockPrice: StockPriceData) => {
                    categories.push(stockPrice.dataPoint);
                    companyData.push(stockPrice.dataValue);
                })
                let series = [{
                    name: this.compareData.companies[0].companyCode + " (" + this.compareData.companies[0].stockExchange + ")",
                    data: companyData
                }]
                this.getSecondDataComplete = true;
                this.chartTwoOptions = {
                    chart: {
                        type: "column"
                    },
                    title: {
                        text: "Company 2"
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
            });
        }
    }
}
