import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators, AbstractControl, FormArray } from "@angular/forms";
import { faPlus } from "@fortawesome/free-solid-svg-icons"
import { StockExchangesService } from 'src/app/services/stock-exchanges.service';
import { StockExchange } from 'src/app/models/stockExchange';
import { StockPriceService } from 'src/app/services/stock-price.service';
import { CompaniesService } from 'src/app/services/companies.service';
import { Company } from 'src/app/models/company';

@Component({
    selector: "app-compare",
    templateUrl: "./compare.component.html",
    styleUrls: ["./compare.component.css"]
})
export class CompareComponent implements OnInit {
    companyCompareForm: FormGroup;
    sectorCompareForm: FormGroup;
    faPlus = faPlus;

    companies: Company[]

    companyComparision: boolean = false;
    sectorComparision: boolean = false;
    canAddAnther: boolean = true;

    maxDate: string = (new Date(Date.now())).toISOString().split('T')[0];
    minDate: string = (new Date("2019-08-08")).toISOString().split('T')[0];


    stockExchanges: StockExchange[];

    constructor(
        private formBuilder: FormBuilder, 
        private stockExService: StockExchangesService, 
        private stockPriceService: StockPriceService, 
        private companiesService: CompaniesService
    ) { }

    ngOnInit() {
        this.companyCompareForm = this.formBuilder.group({
            stockExchange: ["", Validators.required],
            companies: this.formBuilder.array([
                this.formBuilder.control('', Validators.required)
            ]),
            periods: this.formBuilder.array([
                this.formBuilder.group({
                    fromDate: ["", Validators.required],
                    toDate: ["", Validators.required]
                })
            ])
        })

        this.stockExService.getAllExchanges().subscribe(data => {
            this.stockExchanges = data;
        });
    }

    enableSelection(e) {
        if (e.target.value == "company") {
            this.companyComparision = true;
            this.sectorComparision = false;
        }
        if (e.target.value == "sector") {
            this.companyComparision = false;
            this.sectorComparision = true;
        }

    }

    onAddSecondCompany() {
        const control = this.formBuilder.control('', Validators.required);
        (<FormArray>this.companyCompareForm.get('companies')).push(control);
        this.canAddAnther = false;
    }

    onAddSecondPeriod() {
        const group = this.formBuilder.group({
            fromDate: ["", Validators.required],
            toDate: ["", Validators.required]
        });
        (<FormArray>this.companyCompareForm.get('periods')).push(group);
        this.canAddAnther = false;
    }

    onStockExchangeSelect(e){
        this.companiesService.getCompaniesByStockExchange(e.target.value).subscribe( data =>{
            this.companies = data;
        });
    }

    getCompanyCode(company:Company): string{
        for(let listedIn of company.listedIn) {
            if(listedIn.stockExchangeName == this.companyCompareForm.get("stockExchange").value){
                return listedIn.stockCode
            }
        }
    }
}
