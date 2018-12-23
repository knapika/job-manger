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
      this.offers = this.offers.slice(0, 400);
    });
  }

  getList(list: any[], isExp: boolean): any[] {
    if(isExp) {
      return list;
    }
    return list.slice(0, 3);
    
  }



  // --------- template functions -------------

  getFavsButtonLabel(isFav: boolean): string {
    return isFav ? 'Del fav' : 'Add to favs';
  }

  getExpandButtonLabel(isExp: boolean): string {
    return isExp ? 'Less' : 'More';
  } 

  getRowHeight(isExp: boolean): string {
    return isExp ? 'auto' : '120px';
  }

  addOfferToFavs(offer: Offer): void {
    if(offer.isFavorite) {
      offer.isFavorite = false;
    } else {
      offer.isFavorite = true;
    }

    // akcja z serwerem
  
  }

  expandRow(offer: Offer): void {
    if(offer.isExpanded) {
      offer.isExpanded = false;
    } else {
      offer.isExpanded = true;
    }
  }
}
