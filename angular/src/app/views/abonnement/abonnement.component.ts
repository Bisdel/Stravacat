import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { Abonnement } from 'src/app/models/abonnement';
import { AbonnementService } from 'src/app/services/abonnement.service';


@Component({
  selector: 'app-abonnement',
  templateUrl: './abonnement.component.html',
  styleUrls: ['./abonnement.component.css']
})
export class AbonnementComponent implements OnInit {
  abonnementForm!: FormGroup | null;
  abonnement$!: Observable<Abonnement[]>;
  pseudoCtrl!: FormControl;
  ageCtrl!: FormControl;
  especeCtrl!: FormControl;
  villeCtrl!: FormControl;
  animalCtrl!: FormControl;
  editing: number = 0;

  constructor(title: Title, private srvAbonnement: AbonnementService, private formBuilder: FormBuilder) {
    title.setTitle("Liste des abonnements");
  }

  ngOnInit(): void {
    this.reload();
  }

  private reload() {
    this.abonnement$ = this.srvAbonnement.findAll();
  }

  ajouter() {
    this.editing = 0;

    this.pseudoCtrl = this.formBuilder.control('', Validators.minLength(1));
    this.ageCtrl = this.formBuilder.control('', Validators.max(100));
    this.especeCtrl = this.formBuilder.control('', Validators.required);
    this.villeCtrl = this.formBuilder.control('', Validators.required);
    this.animalCtrl = this.formBuilder.control('' , Validators.required);

    this.abonnementForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
      animal_id: this.animalCtrl,
    });
  }

  modifier(abonnement: Abonnement) {
    this.editing = abonnement.id;

    this.abonnement$ = this.srvAbonnement.findAll();
    this.pseudoCtrl = this.formBuilder.control(abonnement.pseudo, Validators.minLength(1));
    this.ageCtrl = this.formBuilder.control(abonnement.age, Validators.required);
    this.especeCtrl = this.formBuilder.control(abonnement.espece, Validators.required);
    this.villeCtrl = this.formBuilder.control(abonnement.ville, Validators.required);
    this.animalCtrl = this.formBuilder.control(abonnement.animal_id, Validators.required);

    this.abonnementForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
      animal_id:this.animalCtrl,
    });
  }

  ajouterOuModifier() {
    let addOrEditObs: Observable<Abonnement>;
    const abonnement = {
      id: this.editing,
      pseudo: this.pseudoCtrl.value,
      age: this.ageCtrl.value,
      espece: this.especeCtrl.value,
      ville_id: this.villeCtrl.value,
      animal_id: this.animalCtrl.value

    };
    if (this.editing) {
      addOrEditObs = this.srvAbonnement.edit(abonnement);
    } else {
      addOrEditObs = this.srvAbonnement.add(abonnement);
    }
    addOrEditObs.subscribe(() => this.reload());
    this.stopAjouterOuModifier();
  }

  stopAjouterOuModifier() {
    this.editing = 0;
    this.abonnementForm = null;
  }

  supprimer(abonnement: Abonnement) {
    this.srvAbonnement.delete(abonnement).subscribe(() => this.reload());
  }
}
