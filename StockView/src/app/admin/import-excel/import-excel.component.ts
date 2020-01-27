import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import bsCustomFileInput from 'bs-custom-file-input';

@Component({
  selector: 'app-import-excel',
  templateUrl: './import-excel.component.html',
  styleUrls: ['./import-excel.component.css']
})
export class ImportExcelComponent implements OnInit {

  excelUploadForm : FormGroup;
  constructor() { }

  ngOnInit() {
    bsCustomFileInput.init();
    this.excelUploadForm = new FormGroup({
      'excelFileUpload' : new FormControl('')
    });
  }

  uploadData(){
    console.log(this.excelUploadForm.value);
  }

}
