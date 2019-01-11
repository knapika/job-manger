import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url: string

  constructor() { }

  setUrl(url: string) {
    this.url = url;
  }

  getUrl(): string {
    return this.url;
  }

}
