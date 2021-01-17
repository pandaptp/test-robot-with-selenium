import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { UserRequest, UserResponse } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient) { }

  login(userRequest: UserRequest): Promise<UserResponse> {
    console.log(userRequest)
    return this._http.post<UserRequest>('http://192.168.0.156:8080/login', userRequest).toPromise();
  }

}
