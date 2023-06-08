import { Injectable } from '@angular/core';
import { Abonnement } from '../models/abonnement';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AbonnementService {
  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<Abonnement[]> {
    return this.httpClient.get<Abonnement[]>(`${ environment.apiUrl }/abonnement`);
  }

  public add(abonnement: any): Observable<Abonnement> {
    return this.httpClient.post<Abonnement>(`${ environment.apiUrl }/abonnement`, abonnement);
  }

  public edit(abonnement: any): Observable<Abonnement> {
    return this.httpClient.put<Abonnement>(`${ environment.apiUrl }/abonnement/${ abonnement.id }`, abonnement);
  }

  public delete(abonnement: Abonnement): Observable<void> {
    return this.httpClient.delete<void>(`${ environment.apiUrl }/abonnement/${ abonnement.id }`);
  }
}
