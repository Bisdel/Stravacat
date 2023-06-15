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
  // Déclaration des variables
  abonnementForm!: FormGroup | null; // Formulaire de l'abonnement
  abonnement$!: Observable<Abonnement[]>; // Liste des abonnements
  pseudoCtrl!: FormControl; // Contrôle du champ pseudo
  ageCtrl!: FormControl; // Contrôle du champ âge
  especeCtrl!: FormControl; // Contrôle du champ espèce
  villeCtrl!: FormControl; // Contrôle du champ ville
  animalCtrl!: FormControl; // Contrôle du champ animal
  editing: number = 0; // Identifiant de l'abonnement en cours d'édition

  collectionSize: number = 0; // Taille de la collection d'abonnements
  page: number = 1; // Page actuelle
  pageSize: number = 4; // Taille de la page
  abonnementToModify: Abonnement | null = null; // Abonnement à modifier

  constructor(title: Title, private srvAbonnement: AbonnementService, private formBuilder: FormBuilder) {
    title.setTitle("Liste des abonnements");
  }

  // Méthode appelée lors du rafraîchissement des pays
  refreshCountries() {
    console.log('Page actuelle :', this.page);
    console.log('Taille de la page :', this.pageSize);
  }

  ngOnInit(): void {
    this.reload();

    // Souscrire à la liste des abonnements pour mettre à jour la collectionSize et pageSize
    this.abonnement$.subscribe(abonnement => {
      this.collectionSize = abonnement.length;
      this.pageSize = 10;
    });
  }

  // Recharger la liste des abonnements
  private reload() {
    this.abonnement$ = this.srvAbonnement.findAll();
  }

  // Méthode appelée lors de l'ajout d'un abonnement
  ajouter() {
    this.editing = 0; // Réinitialiser l'identifiant d'édition

    // Initialiser les contrôles du formulaire avec les validateurs requis
    this.pseudoCtrl = this.formBuilder.control('', Validators.minLength(1));
    this.ageCtrl = this.formBuilder.control('', Validators.max(100));
    this.especeCtrl = this.formBuilder.control('', Validators.required);
    this.villeCtrl = this.formBuilder.control('', Validators.required);
    this.animalCtrl = this.formBuilder.control('', Validators.required);

    // Créer le formulaire avec les contrôles correspondants
    this.abonnementForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
      animal_id: this.animalCtrl,
    });
  }

  // Méthode appelée lors de la modification d'un abonnement
  modifier(abonnement: Abonnement) {
    this.editing = abonnement.id; // Définir l'identifiant de l'abonnement en cours d'édition

    this.abonnement$ = this.srvAbonnement.findAll(); // Recharger la liste des abonnements
    this.pseudoCtrl = this.formBuilder.control(abonnement.pseudo, Validators.minLength(1));
    this.ageCtrl = this.formBuilder.control(abonnement.age, Validators.required);
    this.especeCtrl = this.formBuilder.control(abonnement.espece, Validators.required);
    this.villeCtrl = this.formBuilder.control(abonnement.ville, Validators.required);
    this.animalCtrl = this.formBuilder.control(abonnement.animal_id, Validators.required);

    // Créer le formulaire avec les contrôles correspondants et les valeurs de l'abonnement à modifier
    this.abonnementForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
      animal_id: this.animalCtrl,
    });

    // Demander une confirmation pour la modification de l'abonnement
    const confirmDelete = confirm("Voulez-vous vraiment modifier l'abonnement ?");
    if (confirmDelete) {
      console.log("Modification de l'abonnement : ", abonnement);
    } else {
      this.stopAjouterOuModifier();
    }

    // Réinitialiser le formulaire si l'identifiant d'édition est égal à 0
    if (this.editing === 0) {
      this.abonnementForm = null;
    }
  }

  // Afficher une alerte pour la modification des informations
  showAlert() {
    const confirmResult = confirm("Voulez-vous vraiment modifier les informations ?");
    if (confirmResult) {
      this.ajouterOuModifier();
    }
  };

  // Afficher une alerte pour la suppression d'un abonné
  showDelete(abonne: Abonnement) {
    const confirmResult = confirm("Voulez-vous vraiment supprimer l'abonné ?");
    if (confirmResult) {
      this.supprimer(abonne);
    }
  }

  // Méthode appelée lors de l'ajout ou de la modification d'un abonnement
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

    // Vérifier si l'abonnement est en cours d'édition ou d'ajout
    if (this.editing) {
      addOrEditObs = this.srvAbonnement.edit(abonnement); // Modifier l'abonnement existant
    } else {
      addOrEditObs = this.srvAbonnement.add(abonnement); // Ajouter un nouvel abonnement
    }

    // Souscrire à l'observable pour recharger la liste des abonnements après l'ajout ou la modification
    addOrEditObs.subscribe(() => this.reload());
    this.stopAjouterOuModifier(); // Arrêter l'ajout ou la modification
  }

  // Méthode appelée lors de l'annulation de l'ajout
  annulerAjout() {
    this.stopAjouterOuModifier();
  }

  // Arrêter l'ajout ou la modification
  stopAjouterOuModifier() {
    this.editing = 0; // Réinitialiser l'identifiant d'édition
    this.abonnementForm?.reset(); // Réinitialiser le formulaire
    this.abonnementForm = null; // Définir le formulaire à null
  }

  // Supprimer un abonnement
  supprimer(abonnement: Abonnement) {
    const confirmResult = confirm("Voulez-vous vraiment supprimer l'abonnement ?");
    if (confirmResult) {
      console.log("Suppression réussie :", abonnement);
      this.srvAbonnement.delete(abonnement).subscribe(() => this.reload()); // Supprimer l'abonnement et recharger la liste
    }
  }

  // Méthode appelée lors de l'annulation
  annuler() {
    this.stopAjouterOuModifier();
  }
}
