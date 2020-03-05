import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl } from "@angular/forms";
import bsCustomFileInput from "bs-custom-file-input";
import { ComponentFixtureNoNgZone } from '@angular/core/testing';
import { StockPriceService } from 'src/app/services/stock-price.service';

@Component({
    selector: "app-import-excel",
    templateUrl: "./import-excel.component.html",
    styleUrls: ["./import-excel.component.css"]
})
export class ImportExcelComponent implements OnInit {
    excelUploadForm: FormGroup;
    constructor(private stockPriceService:StockPriceService) {}
    file: File;

    ngOnInit() {
        bsCustomFileInput.init();
        this.excelUploadForm = new FormGroup({
            excelFileUpload: new FormControl("")
        });
    }

    onFileChange(e){
        this.file = e.target.files[0];
    }

    uploadData() {
        console.log(this.file);
        const uploadSheetData = new FormData();
        uploadSheetData.append('stocksSheet',this.file,this.file.name);
        this.stockPriceService.uploadStocksSheet(uploadSheetData).subscribe(data => {
            console.log('Uploaded');
        });
    }
}
