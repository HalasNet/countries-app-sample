import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-badge',
    template: `<span class='app-badge {{badgeCls}}'>{{text}}</span>`
})

export class BadgeComponent implements OnInit {
    @Input() public text: string;
    @Input() public badgeCls: string;

	constructor() { }

	ngOnInit() {

  }

}
