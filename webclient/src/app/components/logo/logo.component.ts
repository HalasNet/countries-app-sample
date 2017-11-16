import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-logo',
    templateUrl: './logo.component.html'
})

export class LogoComponent {
    @Input() fontColor = '#63666A';
    @Input() public iconColor = '#E3642B';
}
