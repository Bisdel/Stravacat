import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { FormInputComponent } from './components/form-input/form-input.component';
import { FormSelectComponent } from './components/form-select/form-select.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { AuthenticationInterceptor } from './interceptors/authentication.interceptor';
import { routes } from './routes';
import { FournisseurComponent } from './views/fournisseur/fournisseur.component';
import { HomeComponent } from './views/home/home.component';
import { PageNotFoundComponent } from './views/page-not-found/page-not-found.component';
import { ProduitComponent } from './views/produit/produit.component';
import { FormCardComponent } from './components/form-card/form-card.component';
import { InscriptionComponent } from './views/inscription/inscription.component';
import { FooterComponent } from './components/footer/footer.component';
import { InputComponent } from './components/input/input.component';
import { AbonnesComponent } from './views/abonnes/abonnes.component';
import { VilleComponent } from './views/ville/ville.component';
import { ConnexionComponent } from './views/connexion/connexion.component';
import { ActualiteComponent } from './views/actualite/actualite.component';
import { AbonnementComponent } from './views/abonnement/abonnement.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PageNotFoundComponent,
    FournisseurComponent,
    ProduitComponent,
    NavigationComponent,
    FormCardComponent,
    FormInputComponent,
    FormSelectComponent,
    InscriptionComponent,
    FooterComponent,
    InputComponent,
    AbonnesComponent,
    VilleComponent,
    ConnexionComponent,
    ActualiteComponent,
    AbonnementComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, ReactiveFormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
