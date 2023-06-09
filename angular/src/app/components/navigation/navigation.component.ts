import { Component } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {
  authOk:boolean = this.srvAuth.isLogged();

  constructor(private srvAuth: AuthenticationService) {
  }

  logout(){
    this.srvAuth.logout();
  }
}
