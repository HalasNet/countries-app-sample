import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { UserInfoService, LoginInfoInStorage} from '../user-info.service';
import { ApiRequestService } from './api-request.service';

export interface LoginRequestParam {
    username: string;
    password: string;
}

@Injectable()
export class LoginService {

    public landingPage = '/home/dashboard/order';
    constructor(
        private router: Router,
        private userInfoService: UserInfoService,
        private apiRequest: ApiRequestService
    ) {}


    getToken(username: string, password: string): Observable<any> {
        const me = this;
        const bodyData: LoginRequestParam = {
            'username' : username,
            'password' : password,
        };
        const loginDataSubject: Subject<any> = new Subject<any>();
        let loginInfoReturn: LoginInfoInStorage; // Object that we want to send back to Login Page

        this.apiRequest.post('session', bodyData)
            .subscribe(jsonResp => {
                if (jsonResp !== undefined && jsonResp !== null && jsonResp.operationStatus === 'SUCCESS') {
                    loginInfoReturn = {
                        'success'    : true,
                        'message'    : jsonResp.operationMessage,
                        'landingPage': this.landingPage,
                        'user'       : {
                            'userId'     : jsonResp.item.userId,
                            'email'      : jsonResp.item.emailAddress,
                            'displayName': jsonResp.item.firstName + ' ' + jsonResp.item.lastName,
                            'token'      : jsonResp.item.token,
                        }
                    };

                    // store username and jwt token in session storage to keep user logged in between page refreshes
                    this.userInfoService.storeUserInfo(JSON.stringify(loginInfoReturn.user));
                } else {
                    loginInfoReturn = {
                        'success' : false,
                        'message': jsonResp.msgDesc,
                        'landingPage' : '/login'
                    };
                }
                loginDataSubject.next(loginInfoReturn);
            });

            return loginDataSubject;
    }

    logout(navigatetoLogout = true): void {
        // clear token remove user from local storage to log user out
        this.userInfoService.removeUserInfo();
        if (navigatetoLogout) {
            this.router.navigate(['logout']);
        }
    }
}
