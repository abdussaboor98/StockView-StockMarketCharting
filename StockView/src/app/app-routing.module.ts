import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { SignupComponent } from "./user/signup/signup.component";
import { HomeComponent } from "./common/home/home.component";
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
import { ManageIposComponent } from "./admin/manage-ipos/manage-ipos.component";
import { UpdateIpoComponent } from "./admin/update-ipo/update-ipo.component";
import { NewSectorComponent } from "./admin/new-sector/new-sector.component";
import { ManageSectorsComponent } from "./admin/manage-sectors/manage-sectors.component";
import { UpdateSectorComponent } from "./admin/update-sector/update-sector.component";
import { CompareComponent } from "./user/compare/compare.component";
import { ViewCompaniesComponent } from "./user/view-companies/view-companies.component";
import { ViewExchangesComponent } from "./user/view-exchanges/view-exchanges.component";
import { ViewSectorsComponent } from "./user/view-sectors/view-sectors.component";
import { LandingComponent } from './common/landing/landing.component';
import { ViewIpoComponent } from './user/view-ipo/view-ipo.component';
import { AdminAuthGuard } from './guards/admin-auth.guard';
import { UserAuthGuard } from './guards/user-auth.guard';
import { NoLoginGuard } from './guards/no-login.guard';
import { LoginGuard } from './guards/login.guard';

const routes: Routes = [
    { path: "", redirectTo: "home", pathMatch: "full" },
    { path: "home", component: HomeComponent, canActivate: [NoLoginGuard] },
    { path: "signup", component: SignupComponent, canActivate: [NoLoginGuard] },
    { path: "admin-home", component: AdminHomeComponent,canActivate:[AdminAuthGuard] },
    { path: "user-home", component: UserHomeComponent, canActivate: [UserAuthGuard] },
    { path: "import-excel", component: ImportExcelComponent,canActivate:[AdminAuthGuard] },
    { path: "new-company", component: NewCompanyComponent,canActivate:[AdminAuthGuard] },
    { path: "new-exchange", component: NewExchangeComponent,canActivate:[AdminAuthGuard] },
    { path: "new-ipo", component: NewIpoComponent,canActivate:[AdminAuthGuard] },
    { path: "new-sector", component: NewSectorComponent,canActivate:[AdminAuthGuard] },
    { path: "otp", component: OtpComponent, canActivate: [UserAuthGuard] },
    { path: "manage-exchange", component: ManageExchangeComponent,canActivate:[AdminAuthGuard]},
    { path: "manage-company", component: ManageCompanyComponent,canActivate:[AdminAuthGuard]},
    { path: "manage-ipos", component: ManageIposComponent,canActivate:[AdminAuthGuard] },
    { path: "manage-sectors", component: ManageSectorsComponent,canActivate:[AdminAuthGuard] },
    { path: "update-ipo", component: UpdateIpoComponent,canActivate:[AdminAuthGuard] },
    { path: "update-company", component: UpdateCompanyComponent,canActivate:[AdminAuthGuard] },
    { path: "update-exchange", component: UpdateExchangeComponent,canActivate:[AdminAuthGuard] },
    { path: "update-sector", component: UpdateSectorComponent,canActivate:[AdminAuthGuard] },
    { path: "view-users", component: ViewUsersComponent },
    { path: "view-companies", component: ViewCompaniesComponent, canActivate: [UserAuthGuard] },
    { path: "view-exchanges", component: ViewExchangesComponent, canActivate: [UserAuthGuard] },
    { path: "view-sectors", component: ViewSectorsComponent, canActivate: [UserAuthGuard] },
    { path: "view-ipos", component: ViewIpoComponent, canActivate: [UserAuthGuard] },
    { path: "account", component: AccountComponent, canActivate: [UserAuthGuard] },
    { path: "account-update", component: AccountUpdateComponent , canActivate: [UserAuthGuard]},
    { path: "compare", component: CompareComponent, canActivate: [UserAuthGuard] },
    { path: "landing", component: LandingComponent, canActivate:[LoginGuard]},

];

@NgModule({
    imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' })],
    exports: [RouterModule]
})
export class AppRoutingModule {}
