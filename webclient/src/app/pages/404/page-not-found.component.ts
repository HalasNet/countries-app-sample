import { Component } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-page-not-found',
    templateUrl: './page-not-found.component.html',
    styleUrls: [ './page-not-found.scss'],
})

export class PageNotFoundComponent {

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    console.log('404 : %s', router.url);
  }

}
