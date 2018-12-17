import { Component } from '@angular/core';
import { DataService } from './services/data-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'webclient';

  constructor(private dataService: DataService) {
    this.dataService.test().subscribe(response => console.log(response));
  }
}
