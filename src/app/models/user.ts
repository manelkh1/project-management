import { Admin } from "./admin";
import { Bank } from "./bank";
import { Manager } from "./manager";
import { Member } from "./member";

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
    manager!: Manager ;
    member !: Member ;
    bank !: Bank ;
    admin !: Admin ;
}