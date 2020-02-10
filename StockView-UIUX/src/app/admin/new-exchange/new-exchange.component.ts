import { Component, OnInit } from "@angular/core";
import {
    FormGroup,
    FormControl,
    FormBuilder,
    Validators
} from "@angular/forms";
import { StockExchangesService } from "src/app/services/stock-exchanges.service";
import { Router } from '@angular/router';

@Component({
    selector: "app-new-exchange",
    templateUrl: "./new-exchange.component.html",
    styleUrls: ["./new-exchange.component.css"]
})
export class NewExchangeComponent implements OnInit {
    newExchangeForm: FormGroup;
    constructor(
        private formBuilder: FormBuilder,
        private stockExService: StockExchangesService,
        private router: Router
    ) {}

    ngOnInit() {
        this.newExchangeForm = this.formBuilder.group({
            id: [""],
            name: ["", Validators.required],
            brief: [""],
            contactAddress: ["", Validators.required],
            remarks: ["", Validators.required]
        });
    }

    onSubmit() {
        this.stockExService
            .addStockExchange(this.newExchangeForm.value)
            .subscribe(data => {
                this.goBack();
            });
    }
    
    goBack() {
        this.router.navigate(["manage-exchange"]);
    }
}
