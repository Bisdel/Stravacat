import { Component } from '@angular/core';
import { AppToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-toasts',
  templateUrl: './toasts.component.html',
  styleUrls: ['./toasts.component.css']
})
export class AppToastsComponent {
  constructor(public toastService:AppToastService) {}
}
