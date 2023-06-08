import { Actualite } from "./actualite";
import { Animal } from "./animal";

export class Ville {
    constructor(
        private _id:number,
        private _nom:string,
        private _ambiance:string,
        private _animaux:Animal[],
        private _actualites:Actualite[]
    ) {}
}
