import { Time } from "@angular/common";
import { Animal } from "./animal";
import { Ville } from "./ville";
import { GeoJsonObject } from "geojson";

export class Parcours {
    // public get animal(): Animal[] {
    //     return this._animal;
    // }
    // public set animal(value: Animal[]) {
    //     this._animal = value;
    // }
    public get ville(): Ville {
        return this._ville;
    }
    public set ville(value: Ville) {
        this._ville = value;
    }
    public get temps(): Time {
        return this._temps;
    }
    public set temps(value: Time) {
        this._temps = value;
    }
    public get trace(): GeoJsonObject {
        return this._trace;
    }
    public set trace(value: GeoJsonObject) {
        this._trace = value;
    }
    public get datePublication(): Date {
        return this._datePublication;
    }
    public set datePublication(value: Date) {
        this._datePublication = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    constructor(
        private _id: number,
        private _datePublication: Date,
        private _trace: GeoJsonObject,
        private _temps: Time,
        private _ville: Ville,
        // private _animal: Animal[]
    ){}
}


