import { Component, OnInit } from '@angular/core';
import { Offer } from 'src/app/share/dtos/offer';
import { DataService } from 'src/app/share/services/data-service.service';
import { TEST_USER } from 'src/app/share/utils/consts';

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
    this.dataService.getFavoriteOffers(TEST_USER.id).subscribe(resp => {
      this.favs = resp
      this.loading = false;
    });
  }

  getFavsButtonLabel(isFav: boolean): string {
    return isFav ? 'Del fav' : 'Add to favs';
  }

  addOfferToFavs(offer: Offer): void {
    if (offer.isFavorite) {
      offer.isFavorite = false;
      this.dataService.deleteFavoriteOffer(TEST_USER.id, offer.offerID).subscribe(delResp => 
        this.dataService.getFavoriteOffers(TEST_USER.id).subscribe(resp => this.favs = resp));
    }
  }
}
