import { Project } from "./project";
import { User } from "./user";

export class Member{
    id!: number;
    user!: User ;
    project!: Project;
}