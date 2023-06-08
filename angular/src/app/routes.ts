import { Routes } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { PageNotFoundComponent } from './views/page-not-found/page-not-found.component';
import { FournisseurComponent } from './views/fournisseur/fournisseur.component';
import { ProduitComponent } from './views/produit/produit.component';
import { authenticatedGuard } from './guards/authenticated.guard';
import { InscriptionComponent } from './views/inscription/inscription.component';
import { AbonnesComponent } from './views/abonnes/abonnes.component';
import { AbonnementComponent } from './views/abonnement/abonnement.component';
import { VilleComponent } from './views/ville/ville.component';
import { ConnexionComponent } from './views/connexion/connexion.component';

// Configuration des routes
export const routes: Routes = [
  // Chaque route a AU MINIMUM un chemin (path) et un composant (component)
  {
    path: 'accueil',
    component: HomeComponent,
    canActivate: [authenticatedGuard]
  },

  {
    path: 'abonne',
    component: AbonnesComponent,
    canActivate: [authenticatedGuard]
  },

  {
    path: 'abonnement',
    component: AbonnementComponent,
    canActivate: [authenticatedGuard]
  },

  {
    path: 'ville',
    component: VilleComponent,
    canActivate: [authenticatedGuard]
  },
  
  {
    path: 'inscription',
    component: InscriptionComponent,
  },

  {
    path: 'connexion',
    component: ConnexionComponent
  },

  {
    path: 'fournisseur',
    component: FournisseurComponent,
    canActivate: [authenticatedGuard]
  },

  {
    path: 'produit',
    component: ProduitComponent,
    canActivate: [authenticatedGuard]
  },

  {
    path: '',
    redirectTo: 'accueil',
    pathMatch: 'full'
  },

  {
    path: '**',
    component: PageNotFoundComponent
  }
];