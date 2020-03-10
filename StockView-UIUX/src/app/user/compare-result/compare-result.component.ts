import { Component, OnInit } from "@angular/core";
import { StockPriceService } from "src/app/services/stock-price.service";
import { StockPrice } from "src/app/models/stockPrice";
import * as Highcharts from 'highcharts';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: "app-compare-result",
    templateUrl: "./compare-result.component.html",
    styleUrls: ["./compare-result.component.css"]
})
export class CompareResultComponent implements OnInit {
    // stockPrices: StockPrice[];
    // highcharts = Highcharts;
    // data: any[] = [];
    // prices:any[] = [];
    // xAxis: any[] = [];
    // chartOptions: any;
    constructor(private stockPriceService: StockPriceService,private route:ActivatedRoute) { }

    ngOnInit() {

        console.log(JSON.parse(this.route.snapshot.queryParams.formData));
        // this.stockPriceService.getCompanyStockPricesBetween(new CompanyStockPriceRequest("500112","BSE",new Date("2019-06-07"),new Date("2019-06-10"),7)).subscribe(data =>{
        //     console.log(data);
        // })
    //     this.stockPriceService.getCompanyStockPricesBetween("500870","BSE",new Date("2019-06-07"),new Date("2019-06-15"),7).subscribe(data =>{
    //         console.log(data);
    //         for(let stockPrice of data){
    //             console.log(stockPrice);
    //             this.prices.push(stockPrice.avgPrice)
    //             let utcDate = new Date(stockPrice.date)
    //             this.xAxis.push(utcDate.getUTCDate()+"/"+(utcDate.getUTCMonth()+1)+"/"+utcDate.getUTCFullYear())
    //         }
        
       
    //     this.data = [{
    //         name: data[0].companyCode +" ("+data[0].stockExchange+")",
    //         data: this.prices
    //     },
    //     {
    //         name: data[0].companyCode +" ("+data[0].stockExchange+")",
    //         data: this.prices
    //     }];

        
    //     this.chartOptions = {
    //         chart: {
    //             type: "column"
    //         },
    //         title: {
    //             text: "Stock Prices Comparisions"
    //         },
    //         xAxis: {
    //             categories: this.xAxis
    //         },
    //         yAxis: {
    //             title: {
    //                 text: "Stock Prices in ₹"
    //             }
    //         },
    //         series: this.data
    //     };
    //  })
    }
}
