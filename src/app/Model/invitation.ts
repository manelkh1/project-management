import { Project } from "./project";
import { Status } from "./status";
import { User } from "./user";

export class Invitation{
    id!: number;
    date!:string;
    user!: User;
    project!: Project;
    status!: Status;
  
}