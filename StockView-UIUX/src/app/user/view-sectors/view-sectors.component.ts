import { Component, OnInit } from "@angular/core";
import { Sector } from "src/app/models/sector";
import { SectorsService } from "src/app/services/sectors.service";

@Component({
    selector: "app-view-sectors",
    templateUrl: "./view-sectors.component.html",
    styleUrls: ["./view-sectors.component.css"]
})
export class ViewSectorsComponent implements OnInit {
    sectors: Sector[];
    constructor(private sectorsService: SectorsService) {}

    ngOnInit() {
        this.sectorsService.getAllSectors().subscribe(data => {
            this.sectors = data;
        });
    }
}
