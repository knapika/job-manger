import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataService } from '../services/data-service.service';
import { Offer } from 'src/app/share/dtos/offer';
import { Technology } from 'src/app/share/dtos/technology';
import { City } from 'src/app/share/dtos/city';
import { Category } from 'src/app/share/dtos/category';

const DEFAULT_FILTER = "None"
@Component({
  selector: 'app-offers-list',
  templateUrl: './offers-list.component.html',
  styleUrls: ['./offers-list.component.css']
})
export class OffersListComponent implements OnInit, OnDestroy {

  offers: Offer[];
  filtered: Offer[];

  techFilter: string;
  cityFilter: string;
  categoryFilter: string;


  technologies: Technology[];
  cities: City[];
  categories: Category[];
  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.getTechnologies();
    this.getCities();
    this.getCategories();

    this.getOffers();

  }

  ngOnDestroy() {
  }

  // --------- template functions -------------
  getList(list: any[], isExp: boolean): any[] {
    if (isExp) {
      return list;
    }
    return list.slice(0, 3);
  }

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
    if (offer.isFavorite) {
      offer.isFavorite = false;
    } else {
      offer.isFavorite = true;
    }

    // akcja z serwerem

  }

  expandRow(offer: Offer): void {
    if (offer.isExpanded) {
      offer.isExpanded = false;
    } else {
      offer.isExpanded = true;
    }
  }

  filterOffers(event): void {
    this.filtered = this.offers.filter(offer => this.checkStringProperty(offer.technology, this.techFilter) &&
      this.checkStringProperty(offer.company.locationCity, this.cityFilter) &&
      this.checkStringProperty(offer.category, this.categoryFilter));
    console.log(this.filtered.length);
  }

  private getOffers() {
    this.dataService.getOffers().subscribe(report => {
      this.offers = report
      this.filtered = this.offers.slice(0, 400);
    });
  }

  private getTechnologies() {
    this.dataService.getTechnologies().subscribe(resp => this.technologies = resp);
  }

  private getCities() {
    this.dataService.getCities().subscribe(resp => this.cities = resp);
  }

  private getCategories() {
    this.dataService.getCategories().subscribe(resp => this.categories = resp);
  }

  private checkStringProperty(property: string, condition: string): boolean {
    if (condition === DEFAULT_FILTER || !condition) {
      return true;
    }

    if (!property) {
      return false;
    }

    if (property.toLowerCase() === condition.toLowerCase()) {
      return true;
    }

    return false;
  }
}
