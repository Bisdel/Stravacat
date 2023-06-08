import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from '../models/response/auth-response';
import { environment } from '../environments/environment';
import { AnimalResponse } from '../models/response/animal-response';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private _token: string = '';
  private _animalId:string = ''; 

  public get token(): string {
    return this._token;
  }

  public set token(value: string) {
    // Stockage du jeton dans le navigateur pour le retrouver au refresh de l'appli (et éviter des déconnexions à répétition)
    localStorage.setItem('token', value);

    this._token = value;
  }
  
  public get animalId(): string {
    return this._animalId;
  }
  public set animalId(value: string) {
    localStorage.setItem('animal', value);
    this._animalId = value;
  }

  constructor(private httpClient: HttpClient) {
    // Récupération du jeton stocké dans le navigateur
    this.token = localStorage.getItem('token') ?? '';
  }

  public isLogged() {
    return !!(this.token && this.token != '');
  }

  public register(
    pseudo: string,
    email: string,
    password: string,
    age:number,
    espece:string,
    ville:string,
    options: any
  ) {
    this.httpClient
      .post<AuthResponse>(`${environment.apiUrl}/animal/inscription`, {
        pseudo,
        email,
        password,
        age,
        espece,
        ville,
      })
      .subscribe({
        next: (result) => {
          this.token = result.token;

          if (options.next) {
            options.next(result);
          }
        },

        error: () => {
          if (options.error) {
            options.error();
          }
        },
      });
  }

  public connexion(pseudo: string, password: string, options: any) {
    this.httpClient
      .post<AuthResponse>(`${environment.apiUrl}/animal/connexion`, {
        pseudo,
        password,
      })
      .subscribe({
        next: (result) => {
          this.token = result.token;
          this._animalId = result.animalResponse.id.toString();

          if (options.next) {
            options.next(result);
          }
        },

        error: () => {
          if (options.error) {
            options.error();
          }
        },
      });
  }

}
