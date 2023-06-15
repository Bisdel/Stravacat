import { Time } from "@angular/common";
export class Parcours {
    public get animalId(): string {
        return this._animalId;
    }
    public set animalId(value: string) {
        this._animalId = value;
    }
    public get villeParcours(): string {
        return this._villeParcours;
    }
    public set villeParcours(value: string) {
        this._villeParcours = value;
    }
    public get tempsParcours(): Time {
        return this._tempsParcours;
    }
    public set tempsParcours(value: Time) {
        this._tempsParcours = value;
    }
    public get traceGpsParcours(): string {
        return this._traceGpsParcours;
    }
    public set traceGpsParcours(value: string) {
        this._traceGpsParcours = value;
    }
    public get datePublicationParcours(): Date {
        return this._datePublicationParcours;
    }
    public set datePublicationParcours(value: Date) {
        this._datePublicationParcours = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    constructor(
        private _id: number,
        private _datePublicationParcours: Date,
        private _traceGpsParcours: string,
        private _tempsParcours: Time,
        private _villeParcours: string,
        private _animalId: string
    ){}
}


