import { ParseTreeResult } from '@angular/compiler';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Actualite } from 'src/app/models/actualite';
import { AnimalResponse } from 'src/app/models/response/animal-response';
import { Ville } from 'src/app/models/ville';
import { ActualiteService } from 'src/app/services/actualite.service';
import { AnimalService } from 'src/app/services/animal.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { VilleService } from 'src/app/services/ville.service';

@Component({
  selector: 'app-actualite',
  templateUrl: './actualite.component.html',
  styleUrls: ['./actualite.component.css']
})
export class ActualiteComponent implements OnInit {
  actualites$!: Observable<Actualite[]>;
  villes$!: Observable<Ville[]>;
  actualiteForm?: FormGroup | null;
  animalCtrl!: FormControl;
  villeCtrl!: FormControl;
  descriptionCtrl!: FormControl;
  isprivateCtrl!: FormControl;
  editing: number = 0;
  animalToken: number = parseInt(this.srvAuth.animalId);

  constructor(
    title: Title,
    private srvActualite: ActualiteService,
    private srvVille: VilleService,
    private router: Router,
    private srvAuth: AuthenticationService,
    private srvAnimal: AnimalService,
    private formBuilder: FormBuilder
  ) {
    title.setTitle("Fil d'actualités");
  }
  
  ngOnInit(): void {
    this.actualites$ = this.srvActualite.findAll();
  }

  ajouter() {
    this.editing = 0;
    this.villes$ = this.srvVille.findAll();

    this.villeCtrl = this.formBuilder.control(0, Validators.min(1));
    this.descriptionCtrl = this.formBuilder.control('', Validators.required);
    this.isprivateCtrl = this.formBuilder.control(false, Validators.required);
    
    this.actualiteForm = this.formBuilder.group({
      ville: this.villeCtrl,
      actu_description: this.descriptionCtrl,
      actu_isprivate: this.isprivateCtrl,
     });
  }

  modifier(actualite: Actualite) {
    this.editing = actualite.actu_id;
    this.villes$ = this.srvVille.findAll();

    this.villeCtrl = this.formBuilder.control(actualite.ville.id, Validators.min(1));
    this.descriptionCtrl = this.formBuilder.control(actualite.actu_description, Validators.required);
    this.isprivateCtrl = this.formBuilder.control(actualite.actu_isprivate, Validators.required);
    console.log(actualite.actu_isprivate);

    this.actualiteForm = this.formBuilder.group({
      ville: this.villeCtrl,
      actu_description: this.descriptionCtrl,
      actu_isprivate: this.isprivateCtrl,
    });
  }

  ajouterOuModifier() {
  let addOrEditObs: Observable<Actualite>;
    const actualite = {
      actu_id: this.editing,
      animal: +this.srvAuth.animalId,
      ville: +this.villeCtrl.value,
      actu_description: this.descriptionCtrl.value,
      actu_isprivate: this.isprivateCtrl.value
    };
    console.log(actualite)
   
    // if (confirm("Es-tu sûr de vouloir valider ?")) {
      if (this.editing) {
        addOrEditObs = this.srvActualite.edit(actualite);
      }

      else {
        addOrEditObs = this.srvActualite.add(actualite);
      }

      addOrEditObs.subscribe(() => this.actualites$ = this.srvActualite.findAll());
      this.stopAjouterOuModifier();
    // }
  }

  stopAjouterOuModifier() {
    this.editing = 0;
    this.actualiteForm = null;
  }

  supprimer(actualite: Actualite) {
    if (confirm("Es-tu sûr de vouloir supprimer cette actualité ?")) {
      this.srvActualite.delete(actualite).subscribe(() => this.actualites$ = this.srvActualite.findAll());
    }
  }
}
