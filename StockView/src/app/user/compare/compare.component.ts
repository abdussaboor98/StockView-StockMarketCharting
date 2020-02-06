import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { faPlus } from "@fortawesome/free-solid-svg-icons"

@Component({
    selector: "app-compare",
    templateUrl: "./compare.component.html",
    styleUrls: ["./compare.component.css"]
})
export class CompareComponent implements OnInit {
    compareForm: FormGroup;
    faPlus = faPlus;
    constructor(private formBuilder: FormBuilder) {}

    ngOnInit() { 
        this.compareForm = this.formBuilder.group({
            compantSector: ["", Validators.required],
            stockEx: ["", Validators.required],
            name: ["", Validators.required],
            formatDate: ["", Validators.required],
            toDate: ["", Validators.required]
        });
    }
    
}
