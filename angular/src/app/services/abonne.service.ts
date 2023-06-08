import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Abonne } from '../models/abonne';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AbonneService {
  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<Abonne[]> {
    return this.httpClient.get<Abonne[]>(`${ environment.apiUrl }/abonne`);
  }

  public add(abonne: any): Observable<Abonne> {
    return this.httpClient.post<Abonne>(`${ environment.apiUrl }/abonne`, abonne);
  }

  public edit(abonne: any): Observable<Abonne> {
    return this.httpClient.put<Abonne>(`${ environment.apiUrl }/abonne/${ abonne.id }`, abonne);
  }

  public delete(abonne: Abonne): Observable<void> {
    return this.httpClient.delete<void>(`${ environment.apiUrl }/abonne/${ abonne.id }`);
  }
}
