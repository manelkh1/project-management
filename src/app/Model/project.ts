import { Status } from "./status";

export class Project{
    id!: number;
    title!: string;
    description!: string;
    startDate!: string;
    endDate!: string;
    creationDate!: string;
    status!:Status;
}