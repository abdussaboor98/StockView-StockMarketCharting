import { Component, OnInit } from "@angular/core";
import {
    FormGroup,
    FormControl,
    Validators,
    AbstractControl
} from "@angular/forms";
import bsCustomFileInput from "bs-custom-file-input";
import { ComponentFixtureNoNgZone } from "@angular/core/testing";
import { StockPriceService } from "src/app/services/stock-price.service";

@Component({
    selector: "app-import-excel",
    templateUrl: "./import-excel.component.html",
    styleUrls: ["./import-excel.component.css"]
})
export class ImportExcelComponent implements OnInit {
    excelUploadForm: FormGroup;
    constructor(private stockPriceService: StockPriceService) { }
    file: File;
    isError: boolean = false;
    errorMessage: string = "";

    ngOnInit() {
        bsCustomFileInput.init();
        this.excelUploadForm = new FormGroup({
            excelFileUpload: new FormControl("", [Validators.required, isInExcelFormatValidator])
        });
    }

    onFileChange(e) {
        this.file = e.target.files[0];
    }

    uploadData() {
        this.isError = false;
        const uploadSheetData = new FormData();
        uploadSheetData.append("stocksSheet", this.file, this.file.name);
        this.stockPriceService.uploadStocksSheet(uploadSheetData).subscribe(
            data => {
                console.log("Uploaded");
            },
            error => {
                if(typeof(error.error) == "string"){
                    this.isError = true;
                    this.errorMessage = error.error;
                }
                
            }
        );
    }
}

function isInExcelFormatValidator(control: AbstractControl): { [key: string]: any } | null {
    const valid = control.value.endsWith(".xls") | control.value.endsWith(".xlsx");
    return valid ? null : { isNotExcel: true };
}
