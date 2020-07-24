import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MapService } from '../../services/map.service';
import * as MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import { environment } from '@env/environment';
import * as mapboxgl from 'mapbox-gl';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  @Input() showSearch = false;
  @Input() latitude = 0;
  @Input() longitude = 0;
  @Input() placeholder = '';

  @Output() countChanged: EventEmitter<any> =   new EventEmitter();
  
  constructor(private map: MapService) { }

  ngOnInit() {

     let mapObj = this.map.buildMap(this.showSearch, this.latitude, this.longitude, this.placeholder);

     if (this.showSearch){
      const geocoder = new MapboxGeocoder({
        accessToken: environment.mapBoxToken,
        placeholder: this.placeholder,
        countries: 'es',
        mapboxgl : mapboxgl
      });

      mapObj.addControl(geocoder, 'top-left');

      geocoder.on('result', (e) => {
        this.countChanged.emit(e);
      });
  }
}
}
