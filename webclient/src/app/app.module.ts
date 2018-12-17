import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OffersListComponent } from './offers-list/offers-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListboxModule } from 'primeng/primeng';



@NgModule({
  declarations: [
    AppComponent,
    OffersListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ListboxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
