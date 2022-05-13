import { Role } from "./role";

export class User{
    id!: number;
    firstName!: string;
    lastName!: string;
    photo!: any;
    post!: string;
    email!: string;
    password!: string;
    status!: boolean;
    city!: string;
    country!: string;
    codePostal!: string;
    role!: Role;
}