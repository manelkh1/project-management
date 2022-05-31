import { UserRole } from './userRole';
import { Admin } from './admin';
import { Bank } from './bank';
import { Manager } from './manager';
import { Member } from './member';

export class ChangePassword {
  oldPassword!: string;
  newPassword!: string;
  confirmedNewPassword!: string;
}
