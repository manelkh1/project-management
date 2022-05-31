import { Status } from './status';
import { Manager } from './manager';
import { Member } from './member';
import { Project } from './project';

export class Invitation {
  id!: number;
  date!: string;
  status!: Status;
  manager!: Manager;
  member!: Member;
  project!: Project;
}

export class SendedInvitation {
  projectId!: number;
  memberId!: number;
}
