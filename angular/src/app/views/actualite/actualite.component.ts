import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Actualite } from 'src/app/models/actualite';
import { ActualiteService } from 'src/app/services/actualite.service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-actualite',
  templateUrl: './actualite.component.html',
  styleUrls: ['./actualite.component.css']
})
export class ActualiteComponent implements OnInit {
  actualites$!: Observable<Actualite[]>;
  constructor(title: Title, private srvActualite: ActualiteService, private router: Router, private srvAuth: AuthenticationService, private formBuilder: FormBuilder) {
    title.setTitle("Fil d'actualités");
  }

  ngOnInit(): void {
    this.actualites$ = this.srvActualite.findAll();
  }

  voir() {
  }

  modifier(actualite: Actualite) {
    
  }
  
  supprimer(actualite: Actualite) {

  }
}
