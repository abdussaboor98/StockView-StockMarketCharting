import { Component, OnInit } from "@angular/core";
import { StockExchangesService } from "src/app/services/stock-exchanges.service";
import { StockExchange } from "src/app/models/stockExchange";
import {
    faPlus,
    faTrash,
    faEdit,
    faEye
} from "@fortawesome/free-solid-svg-icons";
import { Router } from "@angular/router";

@Component({
    selector: "app-manage-exchange",
    templateUrl: "./manage-exchange.component.html",
    styleUrls: ["./manage-exchange.component.css"]
})
export class ManageExchangeComponent implements OnInit {
    faPlus = faPlus;
    faEye = faEye;
    faTrash = faTrash;
    faEdit = faEdit;
    stockExchanges: StockExchange[];
    constructor(
        private stockExService: StockExchangesService,
        private router: Router
    ) {}

    ngOnInit() {
        this.stockExService.getAllExchanges().subscribe(data => {
            this.stockExchanges = data;
            console.log(this.stockExchanges);
        });
    }

    gotoNew(){
        this.router.navigate(['new-exchange'])
    }

    updateStockExchange(id) {
        localStorage.removeItem("stockExId");
        localStorage.setItem("stockExId", id);
        this.router.navigate(["update-exchange"]);
    }

    deleteStockExchange(id: number) {
        this.stockExService.deleteStockExchange(id).subscribe(data => {
            this.stockExchanges = this.stockExchanges.filter(
                exchange => exchange.id !== id
            );
        });
    }
}
