import { Company } from "./company";
import { Skill } from "./skill";

export interface Offer {
    offerID: number;
    postingID: number;
    company: Company;
    salaryFrom: number;
    salaryTo: number;
    salaryCurrency: string;
    salaryDuration: string;
    category: string;
    technology: string;
    title: string;
    level: string;
    isFavorite: boolean;
    isExpanded: boolean;
    musts: Skill[];
    nices: Skill[];
    langs: Skill[];
}