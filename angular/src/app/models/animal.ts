import { Actualite } from "./actualite";
import { Parcours } from "./parcours";
import { Ville } from "./ville";

export class Animal {
    public get actualites(): Actualite[] {
        return this._actualites;
    }
    public set actualites(value: Actualite[]) {
        this._actualites = value;
    }
    public get parcours(): Parcours[] {
        return this._parcours;
    }
    public set parcours(value: Parcours[]) {
        this._parcours = value;
    }
    public get ville(): Ville {
        return this._ville;
    }
    public set ville(value: Ville) {
        this._ville = value;
    }
    public get espece(): string {
        return this._espece;
    }
    public set espece(value: string) {
        this._espece = value;
    }
    public get age(): number {
        return this._age;
    }
    public set age(value: number) {
        this._age = value;
    }
    public get isAdmin(): boolean {
        return this._isAdmin;
    }
    public set isAdmin(value: boolean) {
        this._isAdmin = value;
    }
    public get password(): string {
        return this._password;
    }
    public set password(value: string) {
        this._password = value;
    }
    public get email(): string {
        return this._email;
    }
    public set email(value: string) {
        this._email = value;
    }
    public get pseudo(): string {
        return this._pseudo;
    }
    public set pseudo(value: string) {
        this._pseudo = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    constructor(
        private _id: number,
        private _pseudo: string,
        private _email: string,
        private _password: string,
        private _isAdmin: boolean,
        private _age: number,
        private _espece: string,
        private _ville: Ville,
        private _parcours: Parcours[],
        private _actualites: Actualite[]
        ) { }
}
