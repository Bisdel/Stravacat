import { AnimalResponse } from "./animal-response";

export class AuthResponse {
  private _success: boolean = false;
  private _token: string = "";
  private _animalResponse!: AnimalResponse;
  
  public get animalResponse(): AnimalResponse {
    return this._animalResponse;
  }
  public set animalResponse(value: AnimalResponse) {
    this._animalResponse = value;
  }

  public get success(): boolean {
    return this._success;
  }

  public set success(value: boolean) {
    this._success = value;
  }

  public get token(): string {
    return this._token;
  }

  public set token(value: string) {
    this._token = value;
  }
}
