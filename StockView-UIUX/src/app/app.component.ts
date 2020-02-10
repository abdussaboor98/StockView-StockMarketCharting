import { Component, OnInit, AfterViewInit, DoCheck } from "@angular/core";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.css"]
})
export class AppComponent implements DoCheck{
    title = "StockView";
    commonHeader: boolean = true;
    userHeader: boolean = false;
    adminHeader: boolean = false;

    ngDoCheck(){
        if(localStorage.getItem("userType") == "admin" || sessionStorage.getItem("userType") == "admin"){
            this.commonHeader = false;
            this.adminHeader = true;
            this.userHeader = false;    
        }
        else if(localStorage.getItem("userType") == "user" || sessionStorage.getItem("userType") == "user"){
            this.commonHeader = false;
            this.adminHeader = false;
            this.userHeader = true;    
        }
        else if(localStorage.getItem("userType") == null && sessionStorage.getItem("userType") == null){

            this.commonHeader = true;
            this.adminHeader = false;
            this.userHeader = false;    
        }
    }
}
