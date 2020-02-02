import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { AppRoutingModule } from "./app-routing.module";
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from "./app.component";
import { SignupComponent } from "./user/signup/signup.component";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { NewCompanyComponent } from "./admin/new-company/new-company.component";
import { NewExchangeComponent } from "./admin/new-exchange/new-exchange.component";
import { ImportExcelComponent } from "./admin/import-excel/import-excel.component";
import { NewIpoComponent } from "./admin/new-ipo/new-ipo.component";
import { AccountUpdateComponent } from "./user/account-update/account-update.component";
import { AdminHomeComponent } from "./admin/admin-home/admin-home.component";
import { UserHomeComponent } from "./user/user-home/user-home.component";
import { OtpComponent } from "./user/otp/otp.component";
import { ManageCompanyComponent } from "./admin/manage-company/manage-company.component";
import { ManageExchangeComponent } from "./admin/manage-exchange/manage-exchange.component";
import { UpdateCompanyComponent } from "./admin/update-company/update-company.component";
import { ViewUsersComponent } from "./admin/view-users/view-users.component";
import { AccountComponent } from "./user/account/account.component";
import { UpdateExchangeComponent } from "./admin/update-exchange/update-exchange.component";
import { ManageIposComponent } from "./admin/manage-ipos/manage-ipos.component";
import { UpdateIpoComponent } from './admin/update-ipo/update-ipo.component';
import { ManageSectorsComponent } from './admin/manage-sectors/manage-sectors.component';
import { NewSectorComponent } from './admin/new-sector/new-sector.component';
import { UpdateSectorComponent } from './admin/update-sector/update-sector.component';
import { CompareComponent } from './user/compare/compare.component';
import { ViewCompaniesComponent } from './user/view-companies/view-companies.component';
import { ViewExchangesComponent } from './user/view-exchanges/view-exchanges.component';
import { ViewSectorsComponent } from './user/view-sectors/view-sectors.component';

@NgModule({
    declarations: [
        AppComponent,
        SignupComponent,
        HomeComponent,
        LoginComponent,
        NewCompanyComponent,
        NewExchangeComponent,
        ImportExcelComponent,
        NewIpoComponent,
        AccountUpdateComponent,
        AdminHomeComponent,
        UserHomeComponent,
        OtpComponent,
        ManageCompanyComponent,
        ManageExchangeComponent,
        UpdateCompanyComponent,
        ViewUsersComponent,
        AccountComponent,
        UpdateExchangeComponent,
        ManageIposComponent,
        UpdateIpoComponent,
        ManageSectorsComponent,
        NewSectorComponent,
        UpdateSectorComponent,
        CompareComponent,
        ViewCompaniesComponent,
        ViewExchangesComponent,
        ViewSectorsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        FontAwesomeModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {}
