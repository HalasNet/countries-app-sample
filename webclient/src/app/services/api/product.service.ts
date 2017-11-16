import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ReplaySubject } from 'rxjs/ReplaySubject';
import { Subject } from 'rxjs/Subject';
import { TranslateService } from './translate.service';
import { ApiRequestService } from './api-request.service';
import { HttpParams} from '@angular/common/http';

@Injectable()
export class ProductService {
    constructor(
        private apiRequest: ApiRequestService,
        private translate: TranslateService
    ) {}

    getProducts(page?: number, size?: number): Observable<any> {
        const me = this;
        let params: HttpParams = new HttpParams();
        params = params.append('page', typeof page === 'number' ? page.toString() : '0');
        params = params.append('size', typeof page === 'number' ? size.toString() : '1000');

        const productList = new Subject<any>(); // Will use this subject to emit data that we want
        this.apiRequest.get('api/products', params)
            .subscribe(jsonResp => {
                const returnObj = jsonResp.items.map(function(v, i, a) {
                    const newRow = Object.assign({}, v, {
                        listPrice   : me.translate.getCurrencyString(v.listPrice),
                        standardCost: me.translate.getCurrencyString(v.standardCost)
                    });
                    return newRow;
                });
                productList.next(returnObj); // incidentList is a Subject and emits an event thats being listened to by the components
            });

        return productList;
    }

    getProductStatsByQuantityOrdered(): Observable<any> {
        return this.apiRequest.get('api/product-stats-by-quantity');
    }

}
