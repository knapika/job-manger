import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsViewComponent } from './stats-view/stats-view.component';
import {ChartModule} from 'primeng/chart';
import { MatSelectModule } from '@angular/material';



@NgModule({
  imports: [
    CommonModule,
    ChartModule,
    MatSelectModule
  ],
  exports: [
    StatsViewComponent
  ],
  declarations: [StatsViewComponent]
})
export class StatsModule { }
