import { Component, OnInit } from '@angular/core';
import { CompaniesService } from 'src/app/services/companies.service';
import { Company } from 'src/app/models/company';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-company',
  templateUrl: './manage-company.component.html',
  styleUrls: ['./manage-company.component.css']
})
export class ManageCompanyComponent implements OnInit {

  companies: Company[];
  constructor(private companiesService: CompaniesService, private router: Router) { }

  ngOnInit() {
    this.companiesService.getAllCompanies().subscribe(data => {
      this.companies = data;
    });
  }

  deleteCompany(id: number) {
    this.companiesService.deleteCompany(id).subscribe(data => {
      this.companies = this.companies.filter((company) => company.id !== id);
    });
  }

  updateCompany(id: number) {
    localStorage.removeItem('companyId');
    localStorage.setItem('companyId', id.toString());
    this.router.navigate(['update-company']);
  }

}
