import { Component, OnInit } from "@angular/core";
import { SectorsService } from "src/app/services/sectors.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
    selector: "app-new-sector",
    templateUrl: "./new-sector.component.html",
    styleUrls: ["./new-sector.component.css"]
})
export class NewSectorComponent implements OnInit {
    newSectorForm: FormGroup;
    constructor(
        private sectorsService: SectorsService,
        private formBuilder: FormBuilder,
        private router: Router
    ) {}

    ngOnInit() {
        this.newSectorForm = this.formBuilder.group({
            id: [""],
            name: ["", Validators.required],
            brief: ["", Validators.required]
        });
    }

    onSubmit() {
        this.sectorsService
            .addNewSector(this.newSectorForm.value)
            .subscribe(data => {
                this.newSectorForm.reset();
                this.goBack();
            });
    }

    
    goBack() {
        this.router.navigate(["manage-sectors"]);
    }
}
