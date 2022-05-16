import { User } from './user';
export class Login{
    username!: string;
    password!: string;
}

export class LoginResponse{
    token!: string;
    user!: User;
}
