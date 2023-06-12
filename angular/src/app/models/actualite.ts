
import { Animal } from "./animal";
import { Ville } from "./ville";

export class Actualite {
    public get animal(): Animal {
        return this._animal;
    }
    public set animal(value: Animal) {
        this._animal = value;
    }

    public get actu_timestamp(): Date {
        return this._actu_timestamp;
    }
    public set actu_timestamp(value: Date) {
        this._actu_timestamp = value;
    }
    
    public get ville(): Ville {
        return this._ville;
    }
    public set ville(value: Ville) {
        this._ville = value;
    }

    public get actu_coordonneesgps(): string {
        return this._actu_coordonneesgps;
    }
    public set actu_coordonneesgps(value: string) {
        this._actu_coordonneesgps = value;
    }
    
    public get actu_isprivate(): boolean {
        return this._actu_isprivate;
    }
    public set actu_isprivate(value: boolean) {
        this._actu_isprivate = value;
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
        private _actu_isprivate: boolean,
        private _actu_coordonneesgps: string,
        private _actu_timestamp: Date,
        private _animal: Animal,
        private _ville: Ville
        ) { }
}
