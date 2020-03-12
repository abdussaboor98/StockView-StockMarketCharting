import { Component, OnInit } from '@angular/core';
import { CompaniesService } from 'src/app/services/companies.service';
import { Company } from 'src/app/models/company';

@Component({
  selector: 'app-view-companies',
  templateUrl: './view-companies.component.html',
  styleUrls: ['./view-companies.component.css']
})
export class ViewCompaniesComponent implements OnInit {

  companies: Company[];
  companyToDisplay: Company;
  constructor(private companiesService: CompaniesService) { }

  ngOnInit() {
  //   this.companiesService.getAllCompanies().subscribe(data => {
  //     this.companies = data;
  // });
  }

  onInputChange(e){
    this.companiesService.getAllCompaniesByPattern(e.target.value).subscribe(data => {
          this.companies = data;
    });
  }

  onValueChange(e){
    this.companies.forEach(company => {
      if(e.target.value == company.name){
        this.companyToDisplay = company;
      }
    })
  }

}
