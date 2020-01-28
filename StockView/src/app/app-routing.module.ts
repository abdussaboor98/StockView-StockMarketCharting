import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SignupComponent } from './user/signup/signup.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { UserHomeComponent } from './user/user-home/user-home.component';
import { ImportExcelComponent } from './admin/import-excel/import-excel.component';
import { NewCompanyComponent } from './admin/new-company/new-company.component';
import { NewExchangeComponent } from './admin/new-exchange/new-exchange.component';
import { NewIpoComponent } from './admin/new-ipo/new-ipo.component';
import { OtpComponent } from './user/otp/otp.component';



const routes: Routes = [
  { path: '' , component: HomeComponent },
  { path: 'signup', component: SignupComponent } ,
  { path: 'admin-home', component: AdminHomeComponent },
  { path: 'user-home', component: UserHomeComponent },
  { path: 'import-excel', component: ImportExcelComponent },
  { path: 'new-company', component: NewCompanyComponent},
  { path: 'new-exchange', component: NewExchangeComponent},
  { path: 'new-ipo', component: NewIpoComponent},
  { path: 'otp', component: OtpComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
