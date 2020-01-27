import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-new-ipo',
  templateUrl: './new-ipo.component.html',
  styleUrls: ['./new-ipo.component.css']
})
export class NewIpoComponent implements OnInit {

  newIPOForm : FormGroup
  constructor() { }

  ngOnInit() {
    this.newIPOForm = new FormGroup({
      companyName : new FormControl(null),
      sector : new FormControl(''),
      price: new FormControl(''),
      totShares : new FormControl(null),
      openDateTime : new FormControl(null),
      remarks : new FormControl(null)
    })
  }

  onSubmit(){
    console.log(this.newIPOForm.value);
  }
}
