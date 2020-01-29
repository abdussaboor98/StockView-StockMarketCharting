import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { StockExchangesService } from 'src/app/services/stock-exchanges.service';

@Component({
  selector: 'app-new-exchange',
  templateUrl: './new-exchange.component.html',
  styleUrls: ['./new-exchange.component.css']
})
export class NewExchangeComponent implements OnInit {

  newExchangeForm : FormGroup
  constructor(private formBuilder : FormBuilder, private stockExService : StockExchangesService) { }

  ngOnInit() {
    this.newExchangeForm = this.formBuilder.group({
      id : [''],
      name : ['', Validators.required],
      brief : [''],
      contactAddress : ['', Validators.required],
      remarks : ['', Validators.required]
    })
  }

  onSubmit(){
    this.stockExService.addStockExchange(this.newExchangeForm.value).subscribe(data=>{
      console.log('Stock Exchange added');
    })
  }
}
