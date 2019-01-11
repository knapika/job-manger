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
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './share/auth/auth.guard';
import { RegisterComponent } from './register/register.component';


const appRoutes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'offers', component: OffersListComponent, canActivate: [AuthGuard]},
  { path: 'favorites', component: FavoritesListComponent, canActivate: [AuthGuard]},
  { path: 'stats', component: StatsViewComponent, canActivate: [AuthGuard]},
  { path: '**', component: HomeComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
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
