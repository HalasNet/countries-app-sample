import { Component, ViewEncapsulation, ViewChild, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';

import { LogoComponent  } from './components/logo/logo.component';
import { LoginService   } from './services/api/login.service';
import { UserInfoService} from './services/user-info.service';

import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/switchMap';
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';

@Component({
  selector   : 'app-home',
  templateUrl: './home.component.html',
  styleUrls  : ['./home.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent   {

    public showAppAlert = false;
    public appHeaderItems = [
        {
            label   : 'Dashboard',
            href    : '/home/dashboard',
            subNav  : [
                { label: 'Order Stats'  , href: '/home/dashboard/order'  },
                { label: 'Product Stats', href: '/home/dashboard/product'}
            ]
        },
        { label: 'Orders'   , href: '/home/orders'    , subNav: []},
        { label: 'Products' , href: '/home/products'  , subNav: []},
        { label: 'Customers', href: '/home/customers' , subNav: []},
        { label: 'Employees', href: '/home/employees' , subNav: []}
    ];

    public selectedHeaderItemIndex = 0;
    public selectedSubNavItemIndex = 1;
    public userName = '';

    constructor(
        private router: Router,
        private activeRoute: ActivatedRoute,
        private loginService: LoginService,
        private userInfoService: UserInfoService
    ) {
        // This block is to retrieve the data from the routes (routes are defined in app-routing.module.ts)
        router.events
        .filter(event => event instanceof NavigationEnd)
        .map( _ => this.router.routerState.root)
        .map(route => {
            while (route.firstChild) {
                route = route.firstChild;
            }
            return route;
        })
        .mergeMap( route => route.data)
        .subscribe(data => {
            console.log('Route data===: ', data[0]);
            this.selectedHeaderItemIndex = data[0] ? data[0].selectedHeaderItemIndex : -1;
            this.selectedSubNavItemIndex = data[0] ? data[0].selectedSubNavItemIndex : -1;
        });
        this.userName = this.userInfoService.getUserName();

    }

    navbarSelectionChange(val: any) {
        // console.log(val);
    }

    closeAppAlert() {
        this.showAppAlert = false;
    }

}
