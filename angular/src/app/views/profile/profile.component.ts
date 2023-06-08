import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  erreur: boolean = false;
  userForm!: FormGroup;
  pseudoCtrl!: FormControl;
  emailCtrl!: FormControl;
  passwordCtrl!: FormControl;
  ageCtrl!: FormControl;
  especeCtrl!: FormControl;
  villeCtrl!: FormControl;
  @Output() ok: EventEmitter<void> = new EventEmitter<void>();

  constructor(title: Title, private router: Router, private srvAuth: AuthenticationService, private formBuilder: FormBuilder) {
    title.setTitle("Mon profil");
  }
  
  ngOnInit(): void {
    this.pseudoCtrl = this.formBuilder.control('', Validators.required);
    this.emailCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', Validators.required);
    this.ageCtrl = this.formBuilder.control('', Validators.min(1));
    this.especeCtrl = this.formBuilder.control('', Validators.required);
    this.villeCtrl = this.formBuilder.control('', Validators.required);

    this.userForm = this.formBuilder.group({
      pseudo: this.pseudoCtrl,
      email: this.emailCtrl,
      password: this.passwordCtrl,
      age: this.ageCtrl,
      espece: this.especeCtrl,
      ville: this.villeCtrl,
    });
  }

  sInscrire() {
    this.erreur = false;

    this.srvAuth.register(this.pseudoCtrl.value, this.emailCtrl.value, this.passwordCtrl.value, this.ageCtrl.value, this.especeCtrl.value, this.villeCtrl.value, {
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
