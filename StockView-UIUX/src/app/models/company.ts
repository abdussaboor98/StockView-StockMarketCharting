export class Company {
    id: number;
    name: string;
    sector: string;
    ceo: string;
    directors: string[];
    listedIn: ListedIn[];
    turnover: number;
    brief: string;
}

class ListedIn {
    stockExchangeName: string;
    stockCode: string;
}
