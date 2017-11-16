import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector : 'app-legend',
    styleUrls: [ './legend.scss'],
    template : `
    <div class="app-legend-wrap">
        <div *ngFor=" let l of legend " class="app-legend-item">
        <div  [ngStyle]="{'background-color':l.color}" class="app-legend-color"></div>
        <div class="app-legend-value-wrap">
            <div class="app-legend-value">{{l.value}}</div>
            <div class="app-legend-label">{{l.name}}</div>
        </div>
        </div>
    </div>
    `
})

export class LegendComponent {
  @Input() legend: any[];
}
