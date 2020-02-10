import { Component, OnInit, ElementRef } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { IposService } from "src/app/services/ipos.service";
import { Router } from "@angular/router";
import { Company } from 'src/app/models/company';
import { CompaniesService } from 'src/app/services/companies.service';

@Component({
    selector: "app-new-ipo",
    templateUrl: "./new-ipo.component.html",
    styleUrls: ["./new-ipo.component.css"]
})
export class NewIpoComponent implements OnInit {
    newIPOForm: FormGroup;
    companies: Company[];
    stockExchanges: any[];
    constructor(
        private formBuilder: FormBuilder,
        private iposService: IposService,
        private companiesService: CompaniesService,
        private router: Router
    ) {}

    ngOnInit() {
        this.companiesService.getAllCompanies().subscribe(data =>{
            this.companies = data;
        })
        this.newIPOForm = this.formBuilder.group({
            id: [""],
            companyName: ["", Validators.required],
            stockExchange: ["", Validators.required],
            pricePerShare: ["", Validators.required],
            totalShares: ["", Validators.required],
            openDateTime: ["", Validators.required],
            remarks: ["", Validators.required]
        });
    }

    populateExchanges($event){
        const index = $event.target.selectedIndex-1;
        this.stockExchanges = this.companies[index].stockExchanges;
    }

    onSubmit() {
        this.iposService.addNewIPO(this.newIPOForm.value).subscribe(data => {
            this.newIPOForm.reset();
            this.goBack();
        });
    }
    
    goBack() {
        this.router.navigate(["manage-ipos"]);
    }
}
