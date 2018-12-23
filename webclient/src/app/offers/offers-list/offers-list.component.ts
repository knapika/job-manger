import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data-service.service';
import { Offer } from 'src/app/share/dtos/offer';

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



  // --------- template functions -------------

  getButtonLabel(isFav: boolean): string {
    return isFav ? 'Del fav' : 'Add to favs';
  }

  addOfferToFavs(offer: Offer): void {
    if(offer.isFavorite) {
      offer.isFavorite = false;
    } else {
      offer.isFavorite = true;
    }
    
    // akcja z serwerem

   
  }

  expandRow(rowIndex: number): void {
    console.log(rowIndex)
  }
}
