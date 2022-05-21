import { Byte } from '@angular/compiler/src/util';
import { Status } from './status';

export class Attachment {
  id!: number;
  name!: string;
  fileContent!: Byte[];
  creationDate!: Date;
  status!: Status;
}
