import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data-service.service';

@Component({
  selector: 'app-offers-list',
  templateUrl: './offers-list.component.html',
  styleUrls: ['./offers-list.component.css']
})
export class OffersListComponent implements OnInit {

  offers: any[];
  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.dataService.getOffers().subscribe(report => {
      this.offers = report
      console.log(this.offers)
    });
  }

  getList(list: any[]): any[] {
    return list.slice(0, 3);
  }

}
