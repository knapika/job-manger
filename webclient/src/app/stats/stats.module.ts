import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsViewComponent } from './stats-view/stats-view.component';
import {ChartModule} from 'primeng/chart';



@NgModule({
  imports: [
    CommonModule,
    ChartModule
  ],
  exports: [
    StatsViewComponent
  ],
  declarations: [StatsViewComponent]
})
export class StatsModule { }
