import { Component, OnInit } from "@angular/core";
import {
    FormGroup,
    FormControl,
    FormArray,
    Validators,
    FormBuilder
} from "@angular/forms";
import bsCustomFileInput from "bs-custom-file-input";
import { CompaniesService } from "src/app/services/companies.service";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { Router } from "@angular/router";
import { SectorsService } from "src/app/services/sectors.service";
import { Sector } from "src/app/models/sector";
import { StockExchangesService } from 'src/app/services/stock-exchanges.service';
import { StockExchange } from 'src/app/models/stockExchange';

@Component({
    selector: "app-new-company",
    templateUrl: "./new-company.component.html",
    styleUrls: ["./new-company.component.css"]
})
export class NewCompanyComponent implements OnInit {
    faTrash = faTrash;
    newCompanyForm: FormGroup;
    sectors: Sector[];
    stockExchanges: StockExchange[];
    constructor(
        private formBuilder: FormBuilder,
        private companiesService: CompaniesService,
        private router: Router,
        private sectorsService: SectorsService,
        private stockExService: StockExchangesService
    ) {}

    ngOnInit() {
        bsCustomFileInput.init();
        this.sectorsService.getAllSectors().subscribe(data => {
            this.sectors = data;
        });
        this.stockExService.getAllExchanges().subscribe(data =>{
            this.stockExchanges = data;
        });
        this.newCompanyForm = this.formBuilder.group({
            id: [""],
            name: ["", Validators.required],
            sector: ["none", Validators.required],
            ceo: ["", Validators.required],
            directors: this.formBuilder.array(
                [this.formBuilder.control("")]
                ),
            listedIn: this.formBuilder.array([
                this.formBuilder.group({
                    stockExchangeName: ["", Validators.required],
                    stockCode: ["", Validators.required]
                })
            ]),
            turnover: ["", Validators.required],
            brief: ["", Validators.required]
        });
    }

    onSubmit() {
        this.companiesService
            .addCompany(this.newCompanyForm.value)
            .subscribe(data => {
                this.newCompanyForm.reset();
                this.goBack();
            });
    }

    addDirector() {
        const control = this.formBuilder.control("");
        (<FormArray>this.newCompanyForm.get("directors")).push(control);
    }

    removeDirector(i: number) {
        (<FormArray>this.newCompanyForm.get("directors")).removeAt(i);
    }

    addStockExchange() {
        const stockExGroup = this.formBuilder.group({
            stockExchangeName: ["", Validators.required],
            stockCode: ["", Validators.required]
        });
        (<FormArray>this.newCompanyForm.get("listedIn")).push(
            stockExGroup
        );
    }

    removeStockExchange(i: number) {
        (<FormArray>this.newCompanyForm.get("listedIn")).removeAt(i);
    }

    goBack() {
        this.router.navigate(["manage-company"]);
    }
}
