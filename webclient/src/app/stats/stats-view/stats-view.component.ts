import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/share/services/data-service.service';
import { Technology } from 'src/app/share/dtos/technology';
import { City } from 'src/app/share/dtos/city';

const COLORS = [
    "#FF6384",
    "#36A2EB",
    "#FFCE56",
    "green",
    "red",
    "grey",
    "blue",
    "orange",
]

@Component({
    selector: 'app-stats-view',
    templateUrl: './stats-view.component.html',
    styleUrls: ['./stats-view.component.css']
})
export class StatsViewComponent implements OnInit {

    allTechs: any;
    offersByCities: any;
    avgByCities: any;
    techsInCity: any;

    techFilter: string;
    cityFilter: string;

    technologies: Technology[];
    cities: City[];

    constructor(private dataService: DataService) {
        // this.cityFilter = 'Warszawa';
    }

    ngOnInit() {
        this.getTechnologies();
        this.getCities()
        this.getOffersByCity(null);
        this.getTechsByCity(this.cityFilter);
    }

    // template
    filterCities(evt) {
        this.getOffersByCity(this.techFilter);
    }

    // template
    filterTech(evt) {
        this.getTechsByCity(this.cityFilter);
    }

    private getCities() {
        this.dataService.getCities().subscribe(resp => this.cities = resp);
    }

    private getTechnologies() {
        this.dataService.getTechnologies().subscribe(resp => {
            this.technologies = resp;
            const prepared = this.findTheMostPopular(resp, 'technology');
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

    private getOffersByCity(technology: string): void {
        this.dataService.getOffersByCities(technology).subscribe(resp => {
            const prepared = this.findTheMostPopular(resp, 'city');
            this.offersByCities = {
                labels: prepared.map(tech => tech.city),
                datasets: [
                    {
                        label: 'Offers amount',
                        data: prepared.map(tech => tech.count),
                        backgroundColor: "#FF6384",

                    }
                ]
            };

            this.avgByCities = {
                labels: prepared.map(tech => tech.city),
                datasets: [
                    {
                        label: 'AVG salary (PLN per MONTH)',
                        data: prepared.map(tech => Math.round(tech.avg)),
                        backgroundColor: "#FFCE56",
                    }
                ]
            }
        });
    }

    private getTechsByCity(city: string) {
        this.dataService.getTechnologiesByCities(city).subscribe(resp => {
            this.techsInCity = {
                labels: resp.map(tech => tech.technology),
                datasets: [
                    {
                        label: 'AVG salary (PLN)',
                        data: resp.map(tech => tech.count),
                        backgroundColor: "#36A2EB",
                    }]
            }
        });
    }

    private findTheMostPopular(resp: any[], label: string): any[] {
        const sorted = resp.sort((a, b) => b.count - a.count)
        let prepared: any[] = new Array<any>();
        prepared.push(...sorted.slice(0, 8));

        const other: any = {
            label: 'Other', count: sorted.slice(8)
                .map(tech => tech.count).reduce((a, b) => a + b, 0)
        };
        prepared.push(other)

        return prepared;
    }
}
