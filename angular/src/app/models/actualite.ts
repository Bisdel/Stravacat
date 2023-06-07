import { Animal } from "./animal";
import { Ville } from "./ville";

export class Actualite {
    public get ville(): Ville {
        return this._ville;
    }
    public set ville(value: Ville) {
        this._ville = value;
    }

    public get animal(): Animal {
        return this._animal;
    }
    public set animal(value: Animal) {
        this._animal = value;
    }

    public get actu_coordonneesgps(): string {
        return this._actu_coordonneesgps;
    }
    public set actu_coordonneesgps(value: string) {
        this._actu_coordonneesgps = value;
    }
    
    public get actu_isPrivate(): boolean {
        return this._actu_isPrivate;
    }
    public set actu_isPrivate(value: boolean) {
        this._actu_isPrivate = value;
    }

    public get actu_description(): string {
        return this._actu_description;
    }
    public set actu_description(value: string) {
        this._actu_description = value;
    }

    public get actu_id(): number {
        return this._actu_id;
    }
    public set actu_id(value: number) {
        this._actu_id = value;
    }

    constructor(private _actu_id: number,
        private _actu_description: string,
        private _actu_isPrivate: boolean,
        private _actu_coordonneesgps: string,
        private _animal: Animal,
        private _ville: Ville
        ) { }
}
