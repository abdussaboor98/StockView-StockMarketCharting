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
  constructor(private companiesService: CompaniesService) { }

  ngOnInit() {
    this.companiesService.getAllCompanies().subscribe(data => {
      this.companies = data;
  });
  }

}
