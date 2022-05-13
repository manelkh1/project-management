import { Timestamp } from "rxjs/internal/types";
import {User} from "./user"
import {Project} from "./project"
export class Chat{
    id!: number;
    message!: string;
    time!: Timestamp<1>;
    creationDate!: Date;
    user!: User;
    project!: Project;
}