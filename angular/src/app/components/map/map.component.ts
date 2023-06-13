import { Component, AfterViewInit } from '@angular/core';
import * as L from 'leaflet';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ParcoursService } from 'src/app/services/parcours.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements AfterViewInit {

  private map: any;
  private parcours!: any[];
  private indexParcours: number = 0;

  private initMap(): void {
    this.map = L.map('map').setView([43.610684, 3.876514], 14);

    const tiles = L.tileLayer(
      'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      {
        minZoom: 2,
        maxZoom: 19,
        attribution:
          '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
      }
    );

    tiles.addTo(this.map);
    this.srvParcours.findAllByAnimalId(this.srvAuth.animalId).subscribe({
      next: (result) => {
        this.parcours = result;
        L.geoJSON(JSON.parse(this.parcours[this.indexParcours].traceGpsParcours)).addTo(this.map);
      },
    });
  }

  constructor(
    private srvParcours: ParcoursService,
    private srvAuth: AuthenticationService
  ) {}

  ngAfterViewInit(): void {
    this.initMap();
  }

  parcoursPrecedent() {
    if(this.indexParcours > 0){
      this.indexParcours--;
      L.geoJSON(JSON.parse(this.parcours[this.indexParcours].traceGpsParcours)).addTo(this.map);
      this.map.flyTo([43.610684, 3.876514], 14)
    } else{
      triggerErrorToast("Il n'y a pas de parcours depuis le "+this.parcours[this.indexParcours].datePublicationParcours+".");
    }
  }
  
  parcoursSuivant() {
    if(this.indexParcours < this.parcours.length-1){
      this.indexParcours++;
      L.geoJSON(JSON.parse(this.parcours[this.indexParcours].traceGpsParcours)).addTo(this.map);
      this.map.flyTo([43.2863357,5.3630241],13.5)
    } else{
      triggerErrorToast("Il n'y a pas de parcours avant le "+this.parcours[this.indexParcours].datePublicationParcours+".");
    }
  }


}
function triggerErrorToast(message:String) {
  throw new Error('Function not implemented.');
}

