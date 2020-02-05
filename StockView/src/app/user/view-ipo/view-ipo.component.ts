import { Component, OnInit } from '@angular/core';
import { IPO } from 'src/app/models/ipo';
import { IposService } from 'src/app/services/ipos.service';

@Component({
  selector: 'app-view-ipo',
  templateUrl: './view-ipo.component.html',
  styleUrls: ['./view-ipo.component.css']
})
export class ViewIpoComponent implements OnInit {
  ipos: IPO[];
  constructor(private iposService: IposService) {}

  ngOnInit() {
      this.iposService.getAllIPOs().subscribe(data => {
          this.ipos = data;
      })
  }

}
