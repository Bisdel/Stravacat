import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Actualite } from '../models/actualite';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ActualiteService {

  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<Actualite[]> {
    return this.httpClient.get<Actualite[]>(`${ environment.apiUrl }/actualite`);
  }

  public findByAnimalId(animalId: string): Observable<Actualite[]> {
    return this.httpClient.get<Actualite[]>(`${environment.apiUrl}/actualite/animal/${ animalId }`);
  }

  public add(actualite: any): Observable<Actualite> {
    return this.httpClient.post<Actualite>(`${ environment.apiUrl }/actualite`, actualite);
  }

  public edit(actualite: any): Observable<Actualite> {
    return this.httpClient.put<Actualite>(`${ environment.apiUrl }/actualite/${ actualite.actu_id }`, actualite);
  }

  public delete(actualite: Actualite): Observable<void> {
    return this.httpClient.delete<void>(`${ environment.apiUrl }/actualite/${ actualite.actu_id }`);
  }
}
