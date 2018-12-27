import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataService } from '../services/data-service.service';
import { Offer } from 'src/app/share/dtos/offer';
import { Technology } from 'src/app/share/dtos/technology';
import { City } from 'src/app/share/dtos/city';
import { Category } from 'src/app/share/dtos/category';
import { Level } from 'src/app/share/dtos/level';
import { ifStmt } from '@angular/compiler/src/output/output_ast';

const DEFAULT_FILTER = "None"
const SALARY_OPTIONS = [
  {id: 0, from:'<', to: 5000},
  {id: 1, from: 5000, to: 10000},
  {id: 2, from: 10000, to: 15000},
  {id: 3, from: 15000, to: '<'}
]
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
  levelFilter: string;
  salaryFilter: number;

  technologies: Technology[];
  cities: City[];
  categories: Category[];
  levels: Level[];
  salaryThresholds: any[];
  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.getTechnologies();
    this.getCities();
    this.getCategories();
    this.getLevels();
    this.getSalaryThresholds();

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

  getSalaryLabel(option: any): string {
    if(option.to === '<') {
      return 'more than ' + option.from;
    }

    if(option.from === '<') {
      return 'less than ' + option.to;
    }
    return option.from + ' - ' + option.to;
  }

  filterOffers(event): void {
    this.filtered = this.offers.filter(offer => 
      this.isEqual(offer.technology, this.techFilter) &&
      this.isEqual(offer.company.locationCity, this.cityFilter) &&
      this.isEqual(offer.category, this.categoryFilter) &&
      this.isEqual(offer.level, this.levelFilter) &&
      this.validateSalary(offer.salaryFrom, offer.salaryTo, this.salaryFilter));
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

  private getLevels() {
    this.dataService.getLevels().subscribe(resp => this.levels = resp);
  }

  private getSalaryThresholds() {
    this.salaryThresholds = SALARY_OPTIONS;
  }


  private validateSalary(offerFrom: number, offerTo: number, filterIndex: number): boolean {
    if(!offerFrom || !offerTo) {
      return false;
    }

    const salaryRange: any = this.getSalaryRange(this.salaryThresholds[filterIndex]);
    const from = salaryRange.from;
    const to = salaryRange.to;

    return from <= offerFrom && offerFrom <= to || from <= offerTo && offerTo <= to;
  } 

  private getSalaryRange(thresholds: any): any {
    let from = thresholds.from;
    let to = thresholds.to;

    if(from === '<') {
      from = 0;
      return {from: from, to: to};
    }

    if(to === '<') {
      to = 100000;
      return {from: from, to: to};
    }

    return {from: from, to: to}; 
  }

  private isEqual(property: string, condition: string): boolean {
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
