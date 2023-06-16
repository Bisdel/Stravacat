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

  // Cette méthode récupère tous les abonnés depuis l'API
  public findAll(): Observable<Abonne[]> {
    return this.httpClient.get<Abonne[]>(`${ environment.apiUrl }/abonnes`);
  }

// Cette méthode ajoute un nouvel abonné via l'API
  public add(abonne: any): Observable<Abonne> {
    return this.httpClient.post<Abonne>(`${ environment.apiUrl }/abonnes`, abonne);
  }
  // Cette méthode modifie un abonné existant via l'API
  public edit(abonne: any): Observable<Abonne> {
    return this.httpClient.put<Abonne>(`${ environment.apiUrl }/abonnes/${ abonne.id }`, abonne);
  }
  // Cette méthode supprime un abonné via l'API
  public delete(abonne: Abonne): Observable<void> {
    return this.httpClient.delete<void>(`${ environment.apiUrl }/abonnes/${ abonne.id }`);
  }
}
