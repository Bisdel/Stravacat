import { VilleResponse } from "./ville-response";

export class AnimalResponse {
    public get villeResponse(): VilleResponse {
        return this._villeResponse;
    }
    public set villeResponse(value: VilleResponse) {
        this._villeResponse = value;
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
        private _age: number,
        private _espece: string,
        private _villeResponse: VilleResponse,
        ) { }

}
