import { Component, OnInit } from '@angular/core';
import { Offer } from 'src/app/share/dtos/offer';
import { DataService } from 'src/app/share/services/data-service.service';

@Component({
  selector: 'app-favorites-list',
  templateUrl: './favorites-list.component.html',
  styleUrls: ['./favorites-list.component.css']
})
export class FavoritesListComponent implements OnInit {

  favs: Offer[];
  loading: boolean;
  constructor(private dataService: DataService) {
    this.loading = true;
  }

  ngOnInit() {
    const userID = window.localStorage.getItem('userID');
    this.dataService.getFavoriteOffers(+userID).subscribe(resp => {
      this.favs = resp
      this.loading = false;
    });
  }

  getFavsButtonLabel(isFav: boolean): string {
    return isFav ? 'Del fav' : 'Add to favs';
  }

  addOfferToFavs(offer: Offer): void {
    const userID = window.localStorage.getItem('userID');
    if (offer.isFavorite) {
      offer.isFavorite = false;
      this.dataService.deleteFavoriteOffer(+userID, offer.offerID).subscribe(delResp => 
        this.dataService.getFavoriteOffers(+userID).subscribe(resp => this.favs = resp));
    }
  }
}
