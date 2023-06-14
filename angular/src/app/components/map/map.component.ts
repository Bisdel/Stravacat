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
  private loaded: boolean = false;
  private map: any;
  private parcours$!: any[];
  private traceGps: any;
  private indexParcours: number = 0;

  constructor(
    private srvParcours: ParcoursService,
    private srvAuth: AuthenticationService
  ) {}

  ngAfterViewInit(): void {
    this.srvParcours.findAllByAnimalId(this.srvAuth.animalId).subscribe({
      next: (result) => {
        this.parcours$! = result;
        this.traceGps = JSON.parse(
          this.parcours$![this.indexParcours].traceGpsParcours
        );
        this.initMap();
        this.map.flyTo(this.getMeanCoordinates(this.traceGps), 13);
      },
    });
  }

  private initMap(): void {
    this.map = L.map('map').setView([46.8103532, 1.8132262], 5);
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
    L.geoJSON(this.traceGps).addTo(this.map);
  }

  parcoursPrecedent() {
    if (this.indexParcours > 0) {
      this.indexParcours--;
      this.traceGps = JSON.parse(
        this.parcours$![this.indexParcours].traceGpsParcours
      );
      L.geoJSON(this.traceGps).addTo(this.map);
      this.map.flyTo(this.getMeanCoordinates(this.traceGps), 13);
    } else {
      triggerErrorToast(
        "Il n'y a pas de parcours depuis le " +
          this.parcours$![this.indexParcours].datePublicationParcours +
          '.'
      );
    }
  }

  parcoursSuivant() {
    if (this.indexParcours < this.parcours$!.length - 1) {
      this.indexParcours++;
      this.traceGps = JSON.parse(
        this.parcours$![this.indexParcours].traceGpsParcours
      );
      L.geoJSON(this.traceGps).addTo(this.map);
      this.map.flyTo(this.getMeanCoordinates(this.traceGps), 13);
      // this.map.flyTo([43.2863357,5.3630241],13.5)
    } else {
      triggerErrorToast(
        "Il n'y a pas de parcours avant le " +
          this.parcours$![this.indexParcours].datePublicationParcours +
          '.'
      );
    }
  }
  getMeanCoordinates(traceGps: any): [number, number] {
    let coordinates: string[] = traceGps.features[0].geometry.coordinates;
    let xCoordinates: string[] = [];
    let yCoordinates: string[] = [];
    for (let c of coordinates) {
      xCoordinates.push(c[0]);
      yCoordinates.push(c[1]);
    }
    let sumXCoordinate: number = 0;
    let sumYCoordinate: number = 0;
    for (let c of xCoordinates) {
      sumXCoordinate += parseFloat(c);
    }
    for (let c of yCoordinates) {
      sumYCoordinate += parseFloat(c);
    }
    let meanXCoordinate: number = sumXCoordinate / xCoordinates.length;
    let meanYCoordinate: number = sumYCoordinate / yCoordinates.length;
    let meanCoordinates: [number, number] = [meanYCoordinate, meanXCoordinate];
    return meanCoordinates;
  }
}
function triggerErrorToast(message: String) {
  throw new Error('Function not implemented.');
}
