import { Injectable } from '@angular/core';

export interface UserInStorage {
    userId: string;
    email: string;
    displayName: string;
    token: string;
}

export interface LoginInfoInStorage {
    success: boolean;
    message: string;
    landingPage: string;
    user?: UserInStorage;
}

@Injectable()
export class UserInfoService {

    public currentUserKey = 'currentUser';
    public storage: Storage = sessionStorage;

    constructor() {}

    storeUserInfo(userInfoString: string) {
        this.storage.setItem(this.currentUserKey, userInfoString);
    }

    removeUserInfo() {
        this.storage.removeItem(this.currentUserKey);
    }

    getUserInfo(): UserInStorage|null {
        try {
            const userInfoString = this.storage.getItem(this.currentUserKey);
            if (userInfoString) {
                return JSON.parse(userInfoString);
            } else {
                return null;
            }
        } catch (e) {
            return null;
        }
    }

    isLoggedIn(): boolean {
        return this.storage.getItem(this.currentUserKey) ? true : false;
    }

    getUserName(): string {
        const userObj = this.getUserInfo();
        if (userObj !== null) {
            return userObj.displayName;
        }
        return 'no-user';
    }

    getStoredToken(): string|null {
        const userObj = this.getUserInfo();
        if (userObj !== null) {
            return userObj.token;
        }
        return null;
    }
}
