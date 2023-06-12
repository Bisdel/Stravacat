import { Component, EventEmitter, Output } from '@angular/core';
import {
  FormGroup,
  FormControl,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { AnimalResponse } from 'src/app/models/response/animal-response';
import { AnimalService } from 'src/app/services/animal.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  loaded:boolean = false;
  animalId: string = this.srvAuth.animalId;
  animal!: AnimalResponse;

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
    private router: Router,
    private srvAuth: AuthenticationService,
    private formBuilder: FormBuilder,
    private srvAnimal: AnimalService
  ) {
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
        this.pseudoCtrl = this.formBuilder.control(this.animal.pseudo, Validators.minLength(1));
        this.emailCtrl = this.formBuilder.control('', Validators.email);
        this.passwordCtrl = this.formBuilder.control('', Validators.minLength(8));
        this.ageCtrl = this.formBuilder.control(this.animal.age, Validators.min(1));
        this.especeCtrl = this.formBuilder.control(this.animal.espece, Validators.minLength(1));
        this.villeCtrl = this.formBuilder.control(this.animal.ville.nom, Validators.minLength(1));
    
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
    // this.pseudoCtrl = this.formBuilder.control('', Validators.minLength(1));
    // this.emailCtrl = this.formBuilder.control('', Validators.email);
    // this.passwordCtrl = this.formBuilder.control('', Validators.minLength(8));
    // this.ageCtrl = this.formBuilder.control('', Validators.min(1));
    // this.especeCtrl = this.formBuilder.control('', Validators.minLength(1));
    // this.villeCtrl = this.formBuilder.control('', Validators.minLength(1));

    // this.userForm = this.formBuilder.group({
    //   pseudo: this.pseudoCtrl,
    //   email: this.emailCtrl,
    //   password: this.passwordCtrl,
    //   age: this.ageCtrl,
    //   espece: this.especeCtrl,
    //   ville: this.villeCtrl,
    // });
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
}
