import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsViewComponent } from './stats-view/stats-view.component';

@NgModule({
  imports: [
    CommonModule
  ],
  exports: [
    StatsViewComponent
  ],
  declarations: [StatsViewComponent]
})
export class StatsModule { }
