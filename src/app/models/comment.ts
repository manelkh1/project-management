import { Manager } from './manager';
import { Member } from './member';
import { Project } from './project';

export class Comment {
  id!: number;
  message!: string;
  time!: string;
  manager!: Manager;
  member!: Member;
  project!: Project;
  idProject!: number;
}
