import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from 'src/app/share/dtos/offer';
import { Technology } from 'src/app/share/dtos/technology';
import { City } from 'src/app/share/dtos/city';
import { Category } from 'src/app/share/dtos/category';
import { Level } from 'src/app/share/dtos/level';
import { TEST_USER } from 'src/app/share/utils/consts';
import { FavoriteForm } from 'src/app/share/dtos/favorite-form';
import { User } from 'src/app/share/dtos/user';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

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

  getOffers(user: User): Observable<Offer[]> {
    const url = DataService.baseUrl + '/offers';
    return this.http.post<Offer[]>(url, user, {});
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

  addFavoriteOffer(offerID: number): Observable<any> {
    const url = DataService.baseUrl + '/favorite/add';
    return this.http.post<any>(url, new FavoriteForm(TEST_USER.id, offerID), httpOptions);
  }

  getFavoriteOffers(userID: number): Observable<Offer[]> {
    const url = DataService.baseUrl + '/favorites';
    return this.http.post<Offer[]>(url, new FavoriteForm(userID, null), httpOptions);
  }

  deleteFavoriteOffer(userID: number, offerID: number): Observable<any> {
    const url = DataService.baseUrl + '/favorite/delete';
    return this.http.post<any>(url, new FavoriteForm(userID, offerID), httpOptions);
  }
}
