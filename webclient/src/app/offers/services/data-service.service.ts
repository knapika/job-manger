import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from 'src/app/share/dtos/offer';

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
}
