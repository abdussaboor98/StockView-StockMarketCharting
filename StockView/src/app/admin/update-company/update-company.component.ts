import { Component, OnInit } from "@angular/core";
import {
  FormGroup,
  FormControl,
  FormArray,
  Validators,
  FormBuilder
} from "@angular/forms";
import bsCustomFileInput from "bs-custom-file-input";
import { CompaniesService } from 'src/app/services/companies.service';
import { Company } from 'src/app/models/company';
import { faTrash } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: "app-update-company",
  templateUrl: "./update-company.component.html",
  styleUrls: ["./update-company.component.css"]
})
export class UpdateCompanyComponent implements OnInit {
  updateCompanyForm: FormGroup;
  noOfDirectors: number;
  noOfExchanges: number;
  company: Company;

  faTrash = faTrash;

  constructor(private formBuilder: FormBuilder, private companiesService: CompaniesService) { }

  ngOnInit() {
    bsCustomFileInput.init();
    const id = localStorage.getItem('companyId');
    console.log(id);
    if (+id > 0) {
      this.companiesService.getCompayById(+id).subscribe(data => {
        this.noOfDirectors = data.directors.length;
        this.noOfExchanges = data.stockExchanges.length;
        console.log(this.noOfDirectors + ' ' + this.noOfExchanges)
        while (this.noOfDirectors > 0) {
          this.addDirector();
          this.noOfDirectors--;
        }
        while (this.noOfExchanges > 0) {
          this.addStockExchange();
          this.noOfExchanges--;
        }
        this.updateCompanyForm.patchValue(data);
      })
    }

    this.updateCompanyForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      sector: ['', Validators.required],
      ceo: ['', Validators.required],
      directors: this.formBuilder.array([]),
      stockExchanges: this.formBuilder.array([]),
      turnover: ['', Validators.required],
      brief: ['', Validators.required]
    });
  }

  onSubmit() {
    this.companiesService.updateCompany(this.updateCompanyForm.value).subscribe(data => {
      this.updateCompanyForm.reset();
    })
  }

  addDirector() {
    const control = this.formBuilder.control('');
    (<FormArray>this.updateCompanyForm.get('directors')).push(control);
  }

  removeDirector(i: number) {
    (<FormArray>this.updateCompanyForm.get('directors')).removeAt(i);
  }

  addStockExchange() {
    const stockExGroup = this.formBuilder.group({
      stockExchange: ['', Validators.required],
      stockCode: ['', Validators.required]
    });
    (<FormArray>this.updateCompanyForm.get('stockExchanges')).push(stockExGroup);
  }

  removeStockExchange(i: number) {
    (<FormArray>this.updateCompanyForm.get('stockExchanges')).removeAt(i);
  }

}
