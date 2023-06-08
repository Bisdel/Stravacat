import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Ville } from 'src/app/models/ville';
import { Observable } from 'rxjs';
import { Title } from '@angular/platform-browser';
import { VilleService } from 'src/app/services/ville.service';

@Component({
  selector: 'app-ville',
  templateUrl: './ville.component.html',
  styleUrls: ['./ville.component.css']
})
export class VilleComponent implements OnInit {
  erreur: boolean = false;
  villeForm!: FormGroup | null;
  ville$!: Observable<Ville[]>;
  nomCtrl!: FormControl;
  ambianceCtrl!: FormControl;
  editing: number = 0;
  @Output() ok: EventEmitter<void> = new EventEmitter<void>();
  constructor(title: Title, private srvVille: VilleService, private formBuilder: FormBuilder) {
    title.setTitle("Liste des villes");
  }

  ngOnInit(): void {
    this.reload();
  }
  private reload() {
    this.ville$ = this.srvVille.findAll();
  }

  ajouter() {
    this.editing = 0;
    this.nomCtrl = this.formBuilder.control(' ', Validators.required);
    this.ambianceCtrl = this.formBuilder.control(' ', Validators.required);

    this.villeForm = this.formBuilder.group({
      nom: this.nomCtrl,
      ambiance: this.ambianceCtrl,
    });
  }

  modifier(ville: Ville) {
    this.editing = ville.id;
    this.ville$ = this.srvVille.findAll();

    this.nomCtrl = this.formBuilder.control(ville.nom, Validators.required);
    this.ambianceCtrl = this.formBuilder.control(ville.ambiance, Validators.required);

    this.villeForm = this.formBuilder.group({
      nom: this.nomCtrl,
      ambiance: this.ambianceCtrl,
    });
  }

  ajouterOuModifier() {
    let addOrEditObs: Observable<Ville>;
    const ville = {
      id: this.editing,
      nom: this.nomCtrl.value,
      ambiance: this.ambianceCtrl.value,
    };
    if (this.editing) {
      addOrEditObs = this.srvVille.edit(ville);
    }
    else {
      addOrEditObs = this.srvVille.add(ville);
    }
    addOrEditObs.subscribe(() => this.reload());
    this.stopAjouterOuModifer();

  }

  stopAjouterOuModifer() {
    this.editing = 0;
    this.villeForm = null;
  }

  supprimer(ville: Ville) {
    this.srvVille.delete(ville).subscribe(() => this.reload());
  }

}
