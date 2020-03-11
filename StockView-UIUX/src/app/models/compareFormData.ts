export class CompareFormData {
    companies: CompaniesData[];
    periods: Periods[];
    periodicity:string;
}

class CompaniesData{
    companyCode: string;
    stockExchange: string;
}

class Periods {
    fromDate: Date;
    toDate: Date;
}