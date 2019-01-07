import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatFormFieldModule,
MatInputModule } from '@angular/material';
import { OffersListComponent } from './offers/offers-list/offers-list.component';
import { OffersModule } from './offers/offers.module';
import { FavoritesListComponent } from './favorites/favorites-list/favorites-list.component';
import { FavoritesModule } from './favorites/favorites.module';
import { StatsViewComponent } from './stats/stats-view/stats-view.component';
import { StatsModule } from './stats/stats.module';
import {ChartModule} from 'primeng/chart';
import { LoginComponent } from './login/login.component';


const appRoutes: Routes = [
  { path: 'offers', component: OffersListComponent },
  { path: 'favorites', component: FavoritesListComponent },
  { path: 'stats', component: StatsViewComponent },
  { path: '', component: LoginComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
  ],
  imports: [
    OffersModule,
    FavoritesModule,
    StatsModule,
    RouterModule.forRoot(
      appRoutes,
    ),

    ChartModule,
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
    MatListModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
