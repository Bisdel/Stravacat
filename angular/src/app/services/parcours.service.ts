import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Parcours } from '../models/parcours';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';
import { Time } from '@angular/common';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';

@Injectable({
  providedIn: 'root',
})
export class ParcoursService {
  constructor(private httpClient: HttpClient) {}

  public findAllByAnimalId(animalId: string): Observable<Parcours[]> {
    return this.httpClient.get<Parcours[]>(`${environment.apiUrl}/parcours/animal/${animalId}`);
  }

  public ajouterOuModifierParcours(
    villeParcours:string,
    datePublicationParcours:Date,
    tempsParcours: Time,
    traceGpsParcours: JSON,
    animalId: string,
    options:any
  ){
    this.httpClient
      .post<Parcours>(`${environment.apiUrl}/parcours`, {
        villeParcours,
        datePublicationParcours,
        tempsParcours,
        traceGpsParcours,
        animalId
      })
      .subscribe({
        next: (result) => {
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

  public supprimerParcours(
    villeParcours:string,
    datePublicationParcours:Date,
    tempsParcours: Time,
    traceGpsParcours: JSON,
    animalId: string,
    options:any
  ){
    this.httpClient
      .post<Parcours>(`${environment.apiUrl}/parcours`, {
        villeParcours,
        datePublicationParcours,
        tempsParcours,
        traceGpsParcours,
        animalId
      })
      .subscribe({
        next: (result) => {
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
