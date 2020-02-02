import { Component, OnInit } from "@angular/core";
import { Sector } from "src/app/models/sector";
import { SectorsService } from "src/app/services/sectors.service";
import { Router } from "@angular/router";
import { faPlus, faTrash, faEdit } from "@fortawesome/free-solid-svg-icons";

@Component({
    selector: "app-manage-sectors",
    templateUrl: "./manage-sectors.component.html",
    styleUrls: ["./manage-sectors.component.css"]
})
export class ManageSectorsComponent implements OnInit {

    faPlus = faPlus;
    faTrash = faTrash;
    faEdit = faEdit;
    sectors: Sector[];
    constructor(
        private sectorsService: SectorsService,
        private router: Router
    ) {}

    ngOnInit() {
        this.sectorsService.getAllSectors().subscribe(data => {
            this.sectors = data;
        });
    }
    gotoNew() {
        this.router.navigate(["new-sector"]);
    }

    deleteSector(id: number) {
        this.sectorsService.deleteSector(id).subscribe(data => {
            this.sectors = this.sectors.filter(sector => sector.id !== id);
        });
    }

    updateSector(id: number) {
        sessionStorage.removeItem("sectorId");
        sessionStorage.setItem("sectorId", id.toString());
        this.router.navigate(["update-sector"]);
    }
}
