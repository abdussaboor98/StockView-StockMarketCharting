import { Component, OnInit } from "@angular/core";
import {
  FormGroup,
  FormControl,
  FormArray,
  Validators,
  FormBuilder
} from "@angular/forms";
import bsCustomFileInput from "bs-custom-file-input";
import { CompaniesService } from "src/app/services/companies.service";
import { faTrash } from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: "app-new-company",
  templateUrl: "./new-company.component.html",
  styleUrls: ["./new-company.component.css"]
})
export class NewCompanyComponent implements OnInit {
  faTrash = faTrash;
  newCompanyForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private companiesService: CompaniesService
  ) {}

  ngOnInit() {
    bsCustomFileInput.init();
    this.newCompanyForm = this.formBuilder.group({
      id: [""],
      name: ["", Validators.required],
      sector: ["", Validators.required],
      ceo: ["", Validators.required],
      directors: this.formBuilder.array([this.formBuilder.control("")]),
      stockExchanges: this.formBuilder.array([
        this.formBuilder.group({
          stockExchange: ["", Validators.required],
          stockCode: ["", Validators.required]
        })
      ]),
      turnover: ["", Validators.required],
      brief: ["", Validators.required]
    });
  }

  onSubmit() {
    this.companiesService
      .addCompany(this.newCompanyForm.value)
      .subscribe(data => {
        this.newCompanyForm.reset();
      });
  }

  addDirector() {
    const control = this.formBuilder.control("");
    (<FormArray>this.newCompanyForm.get("directors")).push(control);
  }

  removeDirector(i: number) {
    (<FormArray>this.newCompanyForm.get("directors")).removeAt(i);
  }

  addStockExchange() {
    const stockExGroup = this.formBuilder.group({
      stockExchange: ["", Validators.required],
      stockCode: ["", Validators.required]
    });
    (<FormArray>this.newCompanyForm.get("stockExchanges")).push(stockExGroup);
  }

  removeStockExchange(i: number) {
    (<FormArray>this.newCompanyForm.get("stockExchanges")).removeAt(i);
  }
}
