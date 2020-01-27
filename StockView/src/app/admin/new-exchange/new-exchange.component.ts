import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-new-exchange',
  templateUrl: './new-exchange.component.html',
  styleUrls: ['./new-exchange.component.css']
})
export class NewExchangeComponent implements OnInit {

  newExchangeForm : FormGroup
  constructor() { }

  ngOnInit() {
    this.newExchangeForm = new FormGroup({
      exchangeName : new FormControl(null),
      contactAddress : new FormControl(null),
      remarks : new FormControl(null)
    })
  }

  onSubmit(){
    console.log(this.newExchangeForm.value);
  }
}
