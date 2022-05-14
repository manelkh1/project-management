import { Manager } from "./manager";
import { Member } from "./member";
import { Project } from "./project";

export class Invitation{
    id!: number;
    date!:string;
    status!: Enumerator;
    manager : Manager;
    member : Member;
    project : Project;
  
}