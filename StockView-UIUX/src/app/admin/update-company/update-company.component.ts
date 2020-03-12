import { Component, OnInit } from "@angular/core";
import {
    FormGroup,
    FormArray,
    Validators,
    FormBuilder
} from "@angular/forms";
import bsCustomFileInput from "bs-custom-file-input";
import { CompaniesService } from "src/app/services/companies.service";
import { Company } from "src/app/models/company";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { Router, ActivatedRoute } from '@angular/router';
import { StockExchange } from 'src/app/models/stockExchange';
import { StockExchangesService } from 'src/app/services/stock-exchanges.service';

@Component({
    selector: "app-update-company",
    templateUrl: "./update-company.component.html",
    styleUrls: ["./update-company.component.css"]
})
export class UpdateCompanyComponent implements OnInit {
    updateCompanyForm: FormGroup;
    noOfDirectors: number;
    noOfExchanges: number;
    company: Company;
    stockExchanges: StockExchange[];
    faTrash = faTrash;
    companyId: number;
    

    constructor(
        private formBuilder: FormBuilder,
        private companiesService: CompaniesService,
        private router: Router,
        private stockExService: StockExchangesService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        bsCustomFileInput.init();
        let id = this.route.snapshot.params.id
        if (+id > 0) {
            this.companiesService.getCompayById(+id).subscribe(data => {
                this.noOfDirectors = data.directors.length;
                this.noOfExchanges = data.listedIn.length;
                while (this.noOfDirectors > 0) {
                    this.addDirector();
                    this.noOfDirectors--;
                }
                while (this.noOfExchanges > 0) {
                    this.addStockExchange();
                    this.noOfExchanges--;
                }
                this.updateCompanyForm.patchValue(data);
            });
            this.stockExService.getAllExchanges().subscribe(data =>{
                this.stockExchanges = data;
            });
        }

        this.updateCompanyForm = this.formBuilder.group({
            id: [""],
            name: ["", Validators.required],
            sector: ["", Validators.required],
            ceo: ["", Validators.required],
            directors: this.formBuilder.array([]),
            listedIn: this.formBuilder.array([]),
            turnover: ["", Validators.required],
            brief: ["", Validators.required],
            active: [""]
        });
    }

    onSubmit() {
        this.companiesService
            .updateCompany(this.updateCompanyForm.value)
            .subscribe(data => {
                this.updateCompanyForm.reset();
                this.goBack();
            },
            error =>{
                alert("something went wrong:" + error);
            });
    }

    addDirector() {
        const control = this.formBuilder.control("");
        (<FormArray>this.updateCompanyForm.get("directors")).push(control);
    }

    removeDirector(i: number) {
        (<FormArray>this.updateCompanyForm.get("directors")).removeAt(i);
    }

    addStockExchange() {
        const stockExGroup = this.formBuilder.group({
            stockExchangeName: ["", Validators.required],
            stockCode: ["", Validators.required]
        });
        (<FormArray>this.updateCompanyForm.get("listedIn")).push(
            stockExGroup
        );
    }

    removeStockExchange(i: number) {
        (<FormArray>this.updateCompanyForm.get("listedIn")).removeAt(i);
    }

    
    goBack() {
        this.router.navigate(["manage-company"]);
    }
}
