export class Company {
    id: number;
    name: string;
    sector: string;
    ceo: string;
    directors: string[];
    stockExchanges: StockExchange[];
    turnover: number;
    brief: string;
}

class StockExchange {
    stockExchange: string;
    stockCode: string;
}
