import { Component } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  authOk:boolean = this.srvAuth.isLogged();

  constructor(private srvAuth: AuthenticationService) {
  }

  logout(){
    this.srvAuth.logout();
  }
}
