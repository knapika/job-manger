import { Company } from "./company";
import { Skill } from "./skill";
import { Equipment } from "./equipment";

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
    equipment: Equipment;
    isFavorite: boolean;
    isExpanded: boolean;
    musts: Skill[];
    nices: Skill[];
    langs: Skill[];
}