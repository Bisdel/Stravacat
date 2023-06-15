import { Component, AfterViewInit, Output, EventEmitter } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import * as L from 'leaflet';
import { Parcours } from 'src/app/models/parcours';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ParcoursService } from 'src/app/services/parcours.service';
import { AppToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements AfterViewInit {
  private map: any;
  private parcours$!: Parcours[];
  private traceGps: any;
  private indexParcours: number = 0;

  showToast: boolean = false;

  userForm!: FormGroup;
  erreur: boolean = false;
  villeParcoursCtrl!: FormControl;
  datePublicationParcoursCtrl!: FormControl;
  tempsParcoursCtrl!: FormControl;
  traceGpsParcoursCtrl!: FormControl;
  @Output() ok: EventEmitter<void> = new EventEmitter<void>();
  toastMessage: String = '';

  constructor(
    private srvParcours: ParcoursService,
    private srvAuth: AuthenticationService,
    private formBuilder: FormBuilder,
    private srvToast:AppToastService
  ) {
    this.villeParcoursCtrl = this.formBuilder.control(
      '',
      Validators.minLength(1)
    );
    this.datePublicationParcoursCtrl = this.formBuilder.control(
      '',
      Validators.minLength(1)
    );
    this.tempsParcoursCtrl = this.formBuilder.control(
      '',
      Validators.minLength(1)
    );
    this.traceGpsParcoursCtrl = this.formBuilder.control(
      '',
      Validators.minLength(1)
    );

    this.userForm = this.formBuilder.group({
      villeParcours: this.villeParcoursCtrl,
      datePublicationParcours: this.datePublicationParcoursCtrl,
      tempsParcours: this.tempsParcoursCtrl,
      traceGpsParcours: this.traceGpsParcoursCtrl,
    });
  }

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

  ajouterOuModifierParcours() {
    this.erreur = false;
    this.srvParcours.ajouterOuModifierParcours(
      this.villeParcoursCtrl.value,
      this.datePublicationParcoursCtrl.value,
      this.tempsParcoursCtrl.value,
      this.traceGpsParcoursCtrl.value,
      this.srvAuth.animalId.toString(),
      {
        next: () => {
          console.log('ok !');
          window.location.reload();
        },

        error: () => {
          this.erreur = true;
        },
      }
    );
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
      this.srvToast.show('Information',
        "Il n'y a pas de parcours depuis le " +
          this.parcours$[0].datePublicationParcours +
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
    } else {
      this.srvToast.show('Information',
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
