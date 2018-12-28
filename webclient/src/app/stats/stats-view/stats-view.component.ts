import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/share/services/data-service.service';
import { Technology } from 'src/app/share/dtos/technology';

const COLORS = [
    "#FF6384",
    "#36A2EB",
    "#FFCE56",
    "green",
    "red",
    "grey",
    "blue",
]

@Component({
    selector: 'app-stats-view',
    templateUrl: './stats-view.component.html',
    styleUrls: ['./stats-view.component.css']
})
export class StatsViewComponent implements OnInit {

    allTechs: any;

    techFilter: string;
    technologies: Technology[]

    constructor(private dataService: DataService) {
    }

    ngOnInit() {
        this.getTechnologies();
        
    }

    private getTechnologies() {
        this.dataService.getTechnologies().subscribe(resp => {
            this.technologies = resp;
            const sorted = resp.sort((a, b) => b.count - a.count);
            const prepared = this.findTheMostPopular(sorted);
            const total = prepared.map(tech => tech.count).reduce((a, b) => a + b, 0);
            this.allTechs = {
                labels: prepared.map(tech => tech.technology),
                datasets: [
                    {
                        data: prepared.map(tech => Math.round((tech.count / total) * 100)),
                        backgroundColor: COLORS,
                        hoverBackgroundColor: COLORS,
                    }]
            };
        })
    }

    private findTheMostPopular(sorted: Technology[]): Technology[] {
        let prepared: Technology[] = new Array<Technology>();
        prepared.push(...sorted.slice(0,7));
        
        const other: Technology = {technology: 'Other', count: sorted.slice(7)
            .map(tech=> tech.count).reduce((a, b) => a + b, 0)};
        prepared.push(other)
        
        return prepared;
    }
}
