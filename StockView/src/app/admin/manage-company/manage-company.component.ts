import { Component, OnInit } from '@angular/core';
import { CompaniesService } from 'src/app/companies.service';
import { Company } from 'src/app/models/company';

@Component({
  selector: 'app-manage-company',
  templateUrl: './manage-company.component.html',
  styleUrls: ['./manage-company.component.css']
})
export class ManageCompanyComponent implements OnInit {

  companies : any;
  constructor(private companiesService: CompaniesService) { }

  ngOnInit() {
    this.companiesService.getAllCompanies().subscribe(data => {
      this.companies = data;
    });
  }

}
