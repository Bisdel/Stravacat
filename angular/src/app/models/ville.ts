import { Actualite } from "./actualite";
import { Animal } from "./animal";

export class Ville {
    public get actualites(): Actualite[] {
        return this._actualites;
    }
    public set actualites(value: Actualite[]) {
        this._actualites = value;
    }
    public get animaux(): Animal[] {
        return this._animaux;
    }
    public set animaux(value: Animal[]) {
        this._animaux = value;
    }
    public get ambiance(): string {
        return this._ambiance;
    }
    public set ambiance(value: string) {
        this._ambiance = value;
    }
    public get nom(): string {
        return this._nom;
    }
    public set nom(value: string) {
        this._nom = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    constructor(
        private _id: number,
        private _nom: string,
        private _ambiance: string,
        private _animaux: Animal[],
        private _actualites: Actualite[]
    ) { }
}
