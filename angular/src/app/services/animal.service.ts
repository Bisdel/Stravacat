import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { AnimalResponse } from '../models/response/animal-response';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  constructor(private httpClient: HttpClient) { }

  public findById(animalId: string): Observable<AnimalResponse> {
    return this.httpClient.get<AnimalResponse>(`${ environment.apiUrl }/animal/${ animalId }`);
  }

  public add(animal: any): Observable<AnimalResponse> {
    return this.httpClient.post<AnimalResponse>(`${ environment.apiUrl }/animal`, animal.id);
  }

  public edit(animal: any): Observable<AnimalResponse> {
    return this.httpClient.put<AnimalResponse>(`${ environment.apiUrl }/animal/${ animal.id }`, animal);
  }

  public delete(animal: AnimalResponse): Observable<void> {
    return this.httpClient.delete<void>(`${ environment.apiUrl }/animal/${ animal.id }`);
  }

}
