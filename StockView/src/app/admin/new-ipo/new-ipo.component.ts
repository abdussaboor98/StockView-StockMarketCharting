import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

@Component({
    selector: "app-new-ipo",
    templateUrl: "./new-ipo.component.html",
    styleUrls: ["./new-ipo.component.css"]
})
export class NewIpoComponent implements OnInit {
    newIPOForm: FormGroup;
    constructor(private formBuilder: FormBuilder) {}

    ngOnInit() {
        this.newIPOForm = this.formBuilder.group({
            companyName: ["", Validators.required],
            stockExchange: ["", Validators.required],
            pricePerShare: ["", Validators.required],
            totalShares: ["", Validators.required],
            openDateTime: ["", Validators.required],
            remarks: ["", Validators.required]
        });
    }

    onSubmit() {
        console.log(this.newIPOForm.value);
    }
}
