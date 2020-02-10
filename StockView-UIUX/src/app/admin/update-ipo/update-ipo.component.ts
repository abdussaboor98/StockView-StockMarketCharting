import { Component, OnInit, OnDestroy } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { IposService } from "src/app/services/ipos.service";
import { Router } from "@angular/router";

@Component({
    selector: "app-update-ipo",
    templateUrl: "./update-ipo.component.html",
    styleUrls: ["./update-ipo.component.css"]
})
export class UpdateIpoComponent implements OnInit, OnDestroy {
    updateIPOForm: FormGroup;
    constructor(
        private formBuilder: FormBuilder,
        private iposService: IposService,
        private router: Router
    ) {}

    ngOnInit() {
        const id = sessionStorage.getItem("ipoId");
        if (+id > 0) {
            this.iposService.getIPOById(+id).subscribe(data => {
                this.updateIPOForm.patchValue(data);
            });
        } else this.router.navigate(["manage-ipos"]);

        this.updateIPOForm = this.formBuilder.group({
            id: [""],
            companyName: ["", Validators.required],
            stockExchange: ["", Validators.required],
            pricePerShare: ["", Validators.required],
            totalShares: ["", Validators.required],
            openDateTime: ["", Validators.required],
            remarks: ["", Validators.required]
        });
    }

    onSubmit() {
        this.iposService.updateIPO(this.updateIPOForm.value).subscribe(data => {
            this.router.navigate(["manage-ipos"]);
        });
    }

    ngOnDestroy() {
        sessionStorage.removeItem("ipoId");
    }
}
