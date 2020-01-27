import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './user/signup/signup.component';
import { HomeComponent } from './home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { NewCompanyComponent } from './admin/new-company/new-company.component';
import { NewExchangeComponent } from './admin/new-exchange/new-exchange.component';
import { ImportExcelComponent } from './admin/import-excel/import-excel.component';
import { NewIpoComponent } from './admin/new-ipo/new-ipo.component';
import { AccountUpdateComponent } from './user/account-update/account-update.component';


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
    AccountUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
