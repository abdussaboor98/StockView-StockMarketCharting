import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { SignupComponent } from "./user/signup/signup.component";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { AdminHomeComponent } from "./admin/admin-home/admin-home.component";
import { UserHomeComponent } from "./user/user-home/user-home.component";
import { ImportExcelComponent } from "./admin/import-excel/import-excel.component";
import { NewCompanyComponent } from "./admin/new-company/new-company.component";
import { NewExchangeComponent } from "./admin/new-exchange/new-exchange.component";
import { NewIpoComponent } from "./admin/new-ipo/new-ipo.component";
import { OtpComponent } from "./user/otp/otp.component";
import { ManageExchangeComponent } from "./admin/manage-exchange/manage-exchange.component";
import { ManageCompanyComponent } from "./admin/manage-company/manage-company.component";
import { UpdateCompanyComponent } from "./admin/update-company/update-company.component";
import { ViewUsersComponent } from "./admin/view-users/view-users.component";
import { AccountComponent } from "./user/account/account.component";
import { AccountUpdateComponent } from "./user/account-update/account-update.component";
import { UpdateExchangeComponent } from "./admin/update-exchange/update-exchange.component";
import { ManageIposComponent } from './manage-ipos/manage-ipos.component';

const routes: Routes = [
    { path: "", redirectTo: "home", pathMatch: "full" },
    { path: "home", component: HomeComponent },
    { path: "signup", component: SignupComponent },
    { path: "admin-home", component: AdminHomeComponent },
    { path: "user-home", component: UserHomeComponent },
    { path: "import-excel", component: ImportExcelComponent },
    { path: "new-company", component: NewCompanyComponent },
    { path: "new-exchange", component: NewExchangeComponent },
    { path: "new-ipo", component: NewIpoComponent },
    { path: "otp", component: OtpComponent },
    { path: "manage-exchange", component: ManageExchangeComponent },
    { path: "manage-company", component: ManageCompanyComponent },
    { path: "manage-company", component: ManageIposComponent },
    { path: "update-company", component: UpdateCompanyComponent },
    { path: "update-exchange", component: UpdateExchangeComponent },
    { path: "view-users", component: ViewUsersComponent },
    { path: "account", component: AccountComponent },
    { path: "account-update", component: AccountUpdateComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
