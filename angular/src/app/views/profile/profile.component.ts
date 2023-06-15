import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {
  FormGroup,
  FormControl,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { AnimalResponse } from 'src/app/models/response/animal-response';
import { AnimalService } from 'src/app/services/animal.service';
import { Actualite } from 'src/app/models/actualite';
import { Observable } from 'rxjs';
import { ActualiteService } from 'src/app/services/actualite.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  loaded: boolean = false;
  animalId: string = this.srvAuth.animalId;
  animal!: AnimalResponse;
  actualites$!: Observable<Actualite[]>;
  erreur: boolean = false;
  userForm!: FormGroup;
  pseudoCtrl!: FormControl;
  emailCtrl!: FormControl;
  passwordCtrl!: FormControl;
  ageCtrl!: FormControl;
  especeCtrl!: FormControl;
  villeCtrl!: FormControl;
  @Output() ok: EventEmitter<void> = new EventEmitter<void>();

  constructor(
    title: Title,
    private srvActualite: ActualiteService,
    private srvAuth: AuthenticationService,
    private formBuilder: FormBuilder,
    private srvAnimal: AnimalService,
  ) {
    this.actualites$ = this.srvActualite.findByAnimalId(this.srvAuth.animalId);
    title.setTitle('Mon profil');
    this.srvAnimal.findById(this.animalId).subscribe({
      next: (result) => {
        this.animal = new AnimalResponse(
          result.id,
          result.pseudo,
          result.age,
          result.espece,
          result.ville
        );
      
        this.loaded = true;
        this.pseudoCtrl = this.formBuilder.control(
          this.animal.pseudo,
          Validators.minLength(1)
        );
        this.emailCtrl = this.formBuilder.control('', Validators.email);
        this.passwordCtrl = this.formBuilder.control(
          '',
          Validators.minLength(8)
        );
        this.ageCtrl = this.formBuilder.control(
          this.animal.age,
          Validators.min(1)
        );
        this.especeCtrl = this.formBuilder.control(
          this.animal.espece,
          Validators.minLength(1)
        );
        this.villeCtrl = this.formBuilder.control(
          this.animal.ville.nom,
          Validators.minLength(1)
        );

        this.userForm = this.formBuilder.group({
          pseudo: this.pseudoCtrl,
          email: this.emailCtrl,
          password: this.passwordCtrl,
          age: this.ageCtrl,
          espece: this.especeCtrl,
          ville: this.villeCtrl,
        });
      },
    });
  }

  ngOnInit(): void {
  }

  modifier() {
    this.erreur = false;

    this.srvAuth.modifyAnimalData(
      this.srvAuth.animalId,
      this.pseudoCtrl.value,
      this.emailCtrl.value,
      this.passwordCtrl.value,
      this.ageCtrl.value,
      this.especeCtrl.value,
      this.villeCtrl.value,
      {
        next: () => {
          window.location.reload();
        },

        error: () => {
          this.erreur = true;
        },
      }
    );
  }
  doOk() {
    this.ok.emit();
  }

  supprimer() {
    if (confirm('Es-tu sûr de vouloir supprimer ton animal ?')) {
      this.srvAuth.logout();
      this.srvAnimal.delete(this.animal).subscribe({
        next: () => {
          window.location.reload();
        },
      });
    }
  }

  deleteActu(actualite: Actualite) {
    if (confirm('Es-tu sûr de vouloir supprimer cette actualité ?')) {
      this.srvActualite
        .delete(actualite)
        .subscribe(() => this.srvActualite.findAll());
      this.actualites$ = this.srvActualite.findByAnimalId(
        this.srvAuth.animalId
      );
    }
  }
}
