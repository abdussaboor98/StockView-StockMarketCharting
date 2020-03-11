import { Component, OnInit } from "@angular/core";
import { 
    faPlus,
    faTrash,
    faEye,
    faEdit 
} from "@fortawesome/free-solid-svg-icons";
import { IposService } from 'src/app/services/ipos.service';
import { Router } from '@angular/router';
import { IPO } from 'src/app/models/ipo';

@Component({
    selector: "app-manage-ipos",
    templateUrl: "./manage-ipos.component.html",
    styleUrls: ["./manage-ipos.component.css"]
})
export class ManageIposComponent implements OnInit {
    faPlus = faPlus;
    faEye = faEye;
    faTrash = faTrash;
    faEdit = faEdit;
    ipos: IPO[];
    constructor(private iposService: IposService, private router: Router) {}

    ngOnInit() {
        this.iposService.getAllIPOs().subscribe(data => {
            this.ipos = data;
        })
    }

    gotoNew() {
        this.router.navigate(["new-ipo"]);
    }

    deleteIPO(id: number){
        this.iposService.deleteIPO(id).subscribe(data => {
            this.ipos = this.ipos.filter(ipo => ipo.id !== id)
        })
    }

    updateIPO(id: number){
        sessionStorage.removeItem('ipoId');
        sessionStorage.setItem('ipoId',id.toString());
        this.router.navigate(['update-ipo']);
    }
}
