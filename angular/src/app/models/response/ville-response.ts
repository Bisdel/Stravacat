export class VilleResponse {
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
        private _ambiance: string
    ) { }
}
