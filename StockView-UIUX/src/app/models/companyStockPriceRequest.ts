export class CompanyStockPriceRequest {
    companyCode: string;
    stockExchange: string;
    startDate: Date;
    endDate: Date;
    periodicity: number;

    constructor(companyCode: string, stockExchange: string, startDate: Date, endDate: Date, periodicity: number) {
        this.companyCode = companyCode;
        this.stockExchange = stockExchange;
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodicity = periodicity;
    }
}