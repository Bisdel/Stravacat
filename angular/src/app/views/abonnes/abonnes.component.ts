import { Component, OnInit,} from '@angular/core';
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
export class AbonnesComponent implements OnInit {

  abonneForm!: FormGroup | null; // Formulaire de l'abonné
  abonne$!: Observable<Abonne[]>; // Liste des abonnés
  pseudoCtrl!: FormControl; // Contrôle du champ pseudo
  ageCtrl!: FormControl; // Contrôle du champ age
  especeCtrl!: FormControl; // Contrôle du champ espèce
  villeCtrl!: FormControl; // Contrôle du champ ville
  animalCtrl!: FormControl; // Contrôle du champ animal
  editing: number = 0; // Identifiant de l'abonné en cours de modification

  collectionSize: number = 0; // Taille totale de la collection d'abonnés
  page: number = 1; // Page actuelle
  pageSize: number = 4; // Taille de la page (nombre d'abonnés par page)
  abonneToModify: Abonne | null = null; // Abonné en cours de modification
  constructor(title: Title, private srvAbonne: AbonneService, private formBuilder: FormBuilder) {
    title.setTitle("Liste des abonnés"); // Définition du titre de la page
  }
  refreshCountries() {
    console.log('Page actuelle :', this.page); // Affiche la page actuelle
    console.log('Taille de la page :', this.pageSize); // Affiche la taille de la page
  }
  ngOnInit(): void {
    this.reload(); // Chargement des abonnés lors de l'initialisation du composant
    this.abonne$.subscribe(abonnes => {
      this.collectionSize = abonnes.length; // Définition de la taille totale de la collection d'abonnés
      this.pageSize = 10; // Définition de la taille de la page à 10
    });
  }
  private reload() {
    this.abonne$ = this.srvAbonne.findAll(); // Recharge la liste des abonnés depuis le service
  }
  ajouter() {
    this.editing = 0; // Pas de modification en cours (ajout d'un nouvel abonné)
    // Initialisation des contrôles du formulaire avec des valeurs vides et des validateurs requis
    this.pseudoCtrl = this.formBuilder.control('', Validators.required);
    this.ageCtrl = this.formBuilder.control('', Validators.required);
    this.especeCtrl = this.formBuilder.control('', Validators.required);
    this.villeCtrl = this.formBuilder.control('', Validators.required);
    this.animalCtrl = this.formBuilder.control('', Validators.required);
    // Création du formulaire avec les contrôles correspondants
    this.abonneForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
      animal_id: this.animalCtrl,
    });
  }
  modifier(abonne: Abonne) {
    this.editing = abonne.id; // Identifiant de l'abonné en cours de modification
    this.abonne$ = this.srvAbonne.findAll(); // Recharge la liste des abonnés depuis le service
    // Initialisation des contrôles du formulaire avec les valeurs de l'abonné à modifier
    this.pseudoCtrl = this.formBuilder.control(abonne.pseudo, Validators.required);
    this.ageCtrl = this.formBuilder.control(abonne.age, Validators.required);
    this.especeCtrl = this.formBuilder.control(abonne.espece, Validators.required);
    this.villeCtrl = this.formBuilder.control(abonne.ville, Validators.required); // Correction ici
    this.animalCtrl = this.formBuilder.control(abonne.animal_id, Validators.required);
    // Création du formulaire avec les contrôles correspondants
    this.abonneForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
      animal_id: this.animalCtrl
    });
    const confirmDelete = confirm("Voulez-vous vraiment modifier cet abonné ?");
    if (confirmDelete) {
      // Action à effectuer si l'utilisateur répond "Oui"
      console.log("Modification de l'abonné :", abonne);
      // Autres actions à effectuer...
    } else {
      this.stopAjouterOuModifier(); // Masquer le formulaire si l'utilisateur annule
    }
    if (this.editing === 0) {
      this.abonneForm = null; // Si aucun abonné n'est en cours de modification, masquer le formulaire
    }
  }
  showAlert() {
    const confirmResult = confirm("Voulez-vous vraiment modifier les informations ?");
    if (confirmResult) {
      this.ajouterOuModifier();
    }
  };
  showDelete(abonne: Abonne) {
    const confirmResult = confirm("Voulez-vous vraiment supprimer l'abonné ?");
    if (confirmResult) {
      this.supprimer(abonne);
    }
  }
  ajouterOuModifier() {
    let addOrEditObs: Observable<Abonne>;
    const abonne = {
      id: this.editing,
      pseudo: this.pseudoCtrl.value,
      age: this.ageCtrl.value,
      espece: this.especeCtrl.value,
      ville_id: this.villeCtrl.value,
      animal_id: this.animalCtrl.value
    };
    if (this.editing) {
      addOrEditObs = this.srvAbonne.edit(abonne); // Appel du service pour modifier l'abonné existant
    } else {
      addOrEditObs = this.srvAbonne.add(abonne); // Appel du service pour ajouter un nouvel abonné
    }
    addOrEditObs.subscribe(() => this.reload()); // Recharge la liste des abonnés après l'ajout ou la modification
    this.stopAjouterOuModifier();
  }
  annulerAjout() {
    this.stopAjouterOuModifier();
  }
  stopAjouterOuModifier() {
    this.editing = 0; // Pas de modification en cours
    this.abonneForm?.reset(); // Réinitialisation du formulaire
    this.abonneForm = null; // Masquage du formulaire
  }
  supprimer(abonne: Abonne) {
    const confirmResult = confirm("Voulez-vous vraiment supprimer l'abonné ?");
    if (confirmResult) {
      console.log("Suppression de l'abonné :", abonne);
      this.srvAbonne.delete(abonne).subscribe(() => this.reload()); //Appel du service pour supprimer l'abonné
    }
  }
  annuler() {
    this.stopAjouterOuModifier();
  }
}





