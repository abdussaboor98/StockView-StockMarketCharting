import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { SignupComponent } from './user/signup/signup.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NewCompanyComponent } from './admin/new-company/new-company.component';
import { NewExchangeComponent } from './admin/new-exchange/new-exchange.component';
import { ImportExcelComponent } from './admin/import-excel/import-excel.component';
import { NewIpoComponent } from './admin/new-ipo/new-ipo.component';
import { AccountUpdateComponent } from './user/account-update/account-update.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { UserHomeComponent } from './user/user-home/user-home.component';
import { OtpComponent } from './user/otp/otp.component';


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
    OtpComponent
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
export class AppModule { }
