import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OffersListComponent } from './offers-list/offers-list.component';
import {MatButtonModule, MatIconModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule
    
  ],
  exports: [],
  declarations: [OffersListComponent]
})
export class OffersModule { }
