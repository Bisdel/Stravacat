import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ville } from '../models/ville';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VilleService {
  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<Ville[]> {
    return this.httpClient.get<Ville[]>(`${ environment.apiUrl }/ville`);
  }

  public add(ville: any): Observable<Ville> {
    return this.httpClient.post<Ville>(`${ environment.apiUrl }/ville`, ville);
  }

  public edit(ville: any): Observable<Ville> {
    return this.httpClient.put<Ville>(`${ environment.apiUrl }/ville/${ ville.id }`, ville);
  }

  public delete(ville: Ville): Observable<void> {
    return this.httpClient.delete<void>(`${ environment.apiUrl }/ville/${ ville.id }`);
  }
}
