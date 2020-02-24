import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { SectorsService } from "src/app/services/sectors.service";

@Component({
    selector: "app-update-sector",
    templateUrl: "./update-sector.component.html",
    styleUrls: ["./update-sector.component.css"]
})
export class UpdateSectorComponent implements OnInit {
    updateSectorForm: FormGroup;
    constructor(
        private sectorsService: SectorsService,
        private formBuilder: FormBuilder,
        private router: Router
    ) {}

    ngOnInit() {
        const id = sessionStorage.getItem("sectorId");
        if (+id > 0) {
            this.sectorsService.getSectorById(+id).subscribe(data => {
                this.updateSectorForm.patchValue(data);
            });
        } else this.router.navigate(["manage-sectors"]);
        this.updateSectorForm = this.formBuilder.group({
            id: [""],
            name: ["", Validators.required],
            brief: ["", Validators.required]
        });
    }

    onSubmit(){
        this.sectorsService.updateSector(this.updateSectorForm.value).subscribe(data=>{
            this.router.navigate(['manage-sectors'])
        })
    }
}
