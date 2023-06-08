import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent {

  // TODO A implementer :
  // - Acceptation de pseudo OU email dans le même champ pour la connexion
  // - Mot de passe oublié, + envoi d'un mail ?
  erreur: boolean = false;
  userForm!: FormGroup;
  pseudoCtrl!: FormControl;
  passwordCtrl!: FormControl;
  @Output() ok: EventEmitter<void> = new EventEmitter<void>();

  constructor(title: Title, private router: Router, private srvAuth: AuthenticationService, private formBuilder: FormBuilder) {
    title.setTitle("Se connecter");
  }
  
  ngOnInit(): void {
    this.pseudoCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', Validators.required);

    this.userForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      password: this.passwordCtrl,
    });
  }

  connexion() {
    this.erreur = false;

    this.srvAuth.connexion(this.pseudoCtrl.value, this.passwordCtrl.value, {
      next: () => {
        this.router.navigate([ '/profile/'+ this.srvAuth.animalResponse.id]);
      },

      error: () => {
        this.erreur = true;
      }
    });
    
  }
  doOk() {
    this.ok.emit();
  }

}
