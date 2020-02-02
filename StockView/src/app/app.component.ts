import { Component } from "@angular/core";
import * as $ from 'jquery';
import bootstrap from 'bootstrap';

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.css"]
})
export class AppComponent {
    title = "StockView";

    onViewInit(){
        $('.carousel').carousel({interval: 200});
    }
}
