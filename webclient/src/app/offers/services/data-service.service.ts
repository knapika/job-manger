import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from 'src/app/share/dtos/offer';
import { Technology } from 'src/app/share/dtos/technology';
import { City } from 'src/app/share/dtos/city';
import { Category } from 'src/app/share/dtos/category';
import { Level } from 'src/app/share/dtos/level';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private static readonly baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  test(): Observable<string> {
    const url = DataService.baseUrl + '/test';
    return this.http.get<string>(url, {});
  }

  getOffers(): Observable<Offer[]> {
    const url = DataService.baseUrl + '/offers';
    return this.http.get<Offer[]>(url, {});
  }

  getTechnologies(): Observable<Technology[]> {
    const url = DataService.baseUrl + '/offers/technologies';
    return this.http.get<Technology[]>(url, {});
  }

  getCities(): Observable<City[]> {
    const url = DataService.baseUrl + '/companies/cities';
    return this.http.get<City[]>(url, {})
  }

  getCategories(): Observable<Category[]> {
    const url = DataService.baseUrl + '/offers/categories';
    return this.http.get<Category[]>(url, {});
  }

  getLevels(): Observable<Level[]> {
    const url = DataService.baseUrl + '/offers/levels';
    return this.http.get<Level[]>(url, {});
  }
}
