import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';
import { OffersListComponent } from './offers/offers-list/offers-list.component';
import { OffersModule } from './offers/offers.module';
import { FavoritesListComponent } from './favorites/favorites-list/favorites-list.component';
import { FavoritesModule } from './favorites/favorites.module';

const appRoutes: Routes = [
  { path: 'offers', component: OffersListComponent },
  { path: 'favorites', component: FavoritesListComponent },
];

@NgModule({
  
  declarations: [
    AppComponent,
    
  ],
  imports: [
    OffersModule,
    FavoritesModule,
    RouterModule.forRoot(
      appRoutes,
    ),

    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
