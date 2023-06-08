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
}
