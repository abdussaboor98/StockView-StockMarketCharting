import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import bsCustomFileInput from 'bs-custom-file-input';

@Component({
  selector: 'app-new-company',
  templateUrl: './new-company.component.html',
  styleUrls: ['./new-company.component.css']
})
export class NewCompanyComponent implements OnInit {

  newCompanyForm : FormGroup
  constructor() { }

  ngOnInit() {
    bsCustomFileInput.init();
    this.newCompanyForm = new FormGroup({
      "logoUpload" : new FormControl(null),
      "companyName" : new FormControl(null),
      "sector" : new FormControl('none'),
      "ceoName" : new FormControl(null),
      "director" : new FormControl(null),
      "stockExchange" : new FormControl(null),
      "stockCode" : new FormControl(null),
      "turnover" : new FormControl(null),
      "briefDesc" : new FormControl(null),
      "ipoDate" : new FormControl(null)
    })
  }

  onSubmit(){
    
    console.log(this.newCompanyForm.value);
  }

}
