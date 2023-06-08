import { Component, Output, EventEmitter, OnInit  } from '@angular/core';
import { Abonne } from 'src/app/models/abonne';
import { Observable } from 'rxjs';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { AbonneService } from 'src/app/services/abonne.service';


@Component({
  selector: 'app-abonnes',
  templateUrl: './abonnes.component.html',
  styleUrls: ['./abonnes.component.css']
})
export class AbonnesComponent implements OnInit{

  abonneForm!: FormGroup | null;
  abonne$!: Observable<Abonne[]>;
  pseudoCtrl!: FormControl;
  ageCtrl!: FormControl;
  especeCtrl!: FormControl;
  villeCtrl!: FormControl;
  animalCtrl!: FormControl;
  editing: number = 0;
  @Output() ok: EventEmitter<void> = new EventEmitter<void>();

  constructor(title: Title, private srvAbonne: AbonneService, private formBuilder: FormBuilder) {


    title.setTitle("Liste des abonnés");
  }

  ngOnInit(): void {
    this.reload();
  }

  private reload() {
    this.abonne$ = this.srvAbonne.findAll();
  }

  ajouter() {
    this.editing = 0;
    this.pseudoCtrl = this.formBuilder.control(' ', Validators.required);
    this.ageCtrl = this.formBuilder.control('', Validators.required);
    this.especeCtrl = this.formBuilder.control('', Validators.required);
    this.villeCtrl = this.formBuilder.control('', Validators.required);
    this.villeCtrl = this.formBuilder.control('', Validators.required);

    this.abonneForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
      animal: this.animalCtrl,
    });
  }

  modifier(abonne: Abonne) {
    this.editing = abonne.id;
    this.abonne$ = this.srvAbonne.findAll();
    this.pseudoCtrl = this.formBuilder.control(abonne.pseudo, Validators.required);
    this.ageCtrl = this.formBuilder.control(abonne.age, Validators.required);
    this.especeCtrl = this.formBuilder.control(abonne.espece, Validators.required);
    this.villeCtrl = this.formBuilder.control(abonne.ville, Validators.required);
    this.villeCtrl = this.formBuilder.control(abonne.animal, Validators.required);

    this.abonneForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
      animal: this.animalCtrl,
    });
  }

  ajouterOuModifier() {
    let addOrEditObs: Observable<Abonne>;
    const abonne = {
      id: this.editing,
      pseudo: this.pseudoCtrl.value,
      age: this.ageCtrl.value,
      espece: this.especeCtrl.value,
      ville: this.villeCtrl.value,
      animal: this.animalCtrl.value,
    };

    if (this.editing) {
      addOrEditObs = this.srvAbonne.edit(abonne);
    }

    else {
      addOrEditObs = this.srvAbonne.add(abonne);
    }

    addOrEditObs.subscribe(() => this.reload());
    this.stopAjouterOuModifier();
  }


  stopAjouterOuModifier() {
    this.editing = 0;
    this.abonneForm = null;
  }


  supprimer(abonne: Abonne) {
    this.srvAbonne.delete(abonne).subscribe(() => this.reload());
  }

}







