import { Component, OnInit } from "@angular/core";
import {
    FormGroup,
    FormBuilder,
    Validators,
    AbstractControl,
    FormArray
} from "@angular/forms";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { StockExchangesService } from "src/app/services/stock-exchanges.service";
import { StockExchange } from "src/app/models/stockExchange";
import { StockPriceService } from "src/app/services/stock-price.service";
import { CompaniesService } from "src/app/services/companies.service";
import { Company } from "src/app/models/company";
import { Router } from "@angular/router";

@Component({
    selector: "app-compare",
    templateUrl: "./compare.component.html",
    styleUrls: ["./compare.component.css"]
})
export class CompareComponent implements OnInit {
    companyCompareForm: FormGroup;
    sectorCompareForm: FormGroup;
    faPlus = faPlus;

    companies: Company[][] = [];
    stockExchanges: StockExchange[];

    companyComparision: boolean = false;
    sectorComparision: boolean = false;
    canAddAnther: boolean = true;

    maxDate: string = new Date().toISOString().split("T")[0];
    minDate: string = new Date(2016, 1, 1).toISOString().split("T")[0];

    constructor(
        private formBuilder: FormBuilder,
        private stockExService: StockExchangesService,
        private companiesService: CompaniesService,
        private stockPriceService: StockPriceService,
        private router: Router
    ) {}

    ngOnInit() {
        this.companies[0] = [];
        this.companies[1] = [];
        this.companyCompareForm = this.formBuilder.group({
            companies: this.formBuilder.array([
                this.formBuilder.group({
                    stockExchange: ["", Validators.required],
                    companyCode: ["", Validators.required]
                })
            ]),
            periods: this.formBuilder.array([
                this.formBuilder.group({
                    fromDate: ["", Validators.required],
                    toDate: ["", Validators.required]
                })
            ]),
            periodicity: ["", Validators.required]
        });

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
        const group = this.formBuilder.group({
            stockExchange: ["", Validators.required],
            companyCode: ["", Validators.required]
        });
        (<FormArray>this.companyCompareForm.get("companies")).push(group);
        this.canAddAnther = false;
    }

    onAddSecondPeriod() {
        const group = this.formBuilder.group({
            fromDate: ["", Validators.required],
            toDate: ["", Validators.required]
        });
        (<FormArray>this.companyCompareForm.get("periods")).push(group);
        this.canAddAnther = false;
    }

    onStockExchangeSelect(e, i: number) {
        this.companiesService
            .getCompaniesByStockExchange(e.target.value)
            .subscribe(data => {
                this.companies[i] = data;
            });
    }

    getCompanyCode(company: Company, i: number): string {
        for (let listedIn of company.listedIn) {
            if (
                listedIn.stockExchangeName ==
                (<FormArray>this.companyCompareForm.get("companies"))
                    .at(i)
                    .get("stockExchange").value
            ) {
                return listedIn.stockCode;
            }
        }
    }

    // onGetMinMaxDates(e,i){
    //     let companyCode = e.target.value;
    //     let stockExchange = (<FormArray>this.companyCompareForm.get('companies')).get(i).get("stockExchange");
    //     this.stockPriceService.getMinDate(companyCode,stockExchange).subscribe(data => {
    //         this.minDate = data;
    //     })
    //     this.stockPriceService.getMaxDate(companyCode,stockExchange).subscribe(data => {
    //         this.maxDate = data;
    //     });
    // }

    onSubmit() {
        this.router.navigate(["/result"], {
            queryParams: {
                formData: JSON.stringify(this.companyCompareForm.value)
            }
        });
    }
}
