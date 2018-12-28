import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FavoritesListComponent } from './favorites-list/favorites-list.component';
import { MatButtonModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
  ],
  exports: [
    FavoritesListComponent,
  ],
  declarations: [FavoritesListComponent]
})
export class FavoritesModule { }
