import { Component, OnInit } from '@angular/core';
import { StockExchangesService } from 'src/app/stock-exchanges.service';
import { StockExchange } from 'src/app/models/stockExchange';
import { faPlus } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-manage-exchange',
  templateUrl: './manage-exchange.component.html',
  styleUrls: ['./manage-exchange.component.css']
})
export class ManageExchangeComponent implements OnInit {

  faPlus = faPlus;
  stockExchanges : any;
  constructor(private stockExService : StockExchangesService) { }

  ngOnInit() {
    this.stockExService.getAllExchanges().subscribe(data => {
      this.stockExchanges = data;
      console.log(this.stockExchanges);
    })
  }

}
