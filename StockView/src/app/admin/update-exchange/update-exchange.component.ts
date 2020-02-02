import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { StockExchangesService } from "src/app/services/stock-exchanges.service";
import { Router } from "@angular/router";

@Component({
    selector: "app-update-exchange",
    templateUrl: "./update-exchange.component.html",
    styleUrls: ["./update-exchange.component.css"]
})
export class UpdateExchangeComponent implements OnInit {
    updateExchangeForm: FormGroup;
    constructor(
        private formBuilder: FormBuilder,
        private stockExService: StockExchangesService,
        private router: Router
    ) {}

    ngOnInit() {
        const stockExId = localStorage.getItem("stockExId");
        if (+stockExId > 0) {
            this.stockExService.getExchangeById(+stockExId).subscribe(data => {
                this.updateExchangeForm.patchValue(data);
            });
        }
        this.updateExchangeForm = this.formBuilder.group({
            id: [""],
            name: ["", Validators.required],
            brief: [""],
            contactAddress: ["", Validators.required],
            remarks: ["", Validators.required]
        });
    }

    onSubmit() {
        this.stockExService
            .updateStockExchange(this.updateExchangeForm.value)
            .subscribe(data => {
                this.updateExchangeForm.reset();
                this.goBack();
            });
    }

    
    goBack() {
        this.router.navigate(["manage-exchange"]);
    }
}
