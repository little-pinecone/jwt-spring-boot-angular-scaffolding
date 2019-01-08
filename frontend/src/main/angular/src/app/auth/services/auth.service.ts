import { Injectable } from '@angular/core';

import { TokenService } from './token.service'
import { Credentials } from '../credentials';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static readonly TOKEN_STORAGE_KEY = 'token';
  redirectToUrl: string = '/cookies';

  constructor(private router: Router, private tokenService: TokenService) { }

  public login(credentials: Credentials): void {
    this.tokenService.getResponseHeaders(credentials)
    .subscribe((res: HttpResponse<any>) => {
      this.saveToken(res.headers.get('authorization'));
      this.router.navigate([this.redirectToUrl]);
    });
  }

  private saveToken(token: string){
    localStorage.setItem(AuthService.TOKEN_STORAGE_KEY, token);
  }

  public getToken(): string {
    return localStorage.getItem(AuthService.TOKEN_STORAGE_KEY);
  }
}
