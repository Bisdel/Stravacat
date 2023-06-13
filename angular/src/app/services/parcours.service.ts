import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Parcours } from '../models/parcours';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ParcoursService {
  constructor(private httpClient: HttpClient) {}

  public findAllByAnimalId(animalId: string): Observable<Parcours[]> {
    return this.httpClient.get<Parcours[]>(`${environment.apiUrl}/parcours/animal/${animalId}`);
  }
}
