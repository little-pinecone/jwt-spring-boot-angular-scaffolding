import { TestBed, getTestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { AuthService } from './auth.service';
import { TokenService } from './token.service';
import { Credentials } from '../credentials';
import { Observable } from 'rxjs';
import {HttpResponse} from "@angular/common/http";

describe('AuthService', () => {
  let injector: TestBed;
  let service: AuthService;
  let tokenService: TokenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AuthService
       ],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule
      ]
    });
    injector = getTestBed();
    service = injector.get(AuthService);
    tokenService = injector.get(TokenService);
  });

  it('should be created', () => {
    const service: AuthService = TestBed.get(AuthService);
    expect(service).toBeTruthy();
  });

  it('should have redirectToUrl option set to "cookies" by default', () => {
    expect(service.redirectToUrl).toEqual('/cookies');
  });

  it('should call token service for ResponseHeaders when logging in a user', () => {
    spyOn(tokenService, 'getResponseHeaders').and.returnValue(new Observable<HttpResponse<Object>>());
    service.login(new Credentials('',''));
    expect(tokenService.getResponseHeaders).toHaveBeenCalled();
  });

  it('should return false from isLoggedIn() method by default', () => {
    expect(service.isLoggedIn()).toEqual(false);
  });

  it('should return false from isLoggedIn() method when user is logged out', () => {
    spyOn(tokenService, 'logout').and.returnValue(new Observable<string>());
    service.logout();
    expect(service.isLoggedIn()).toEqual(false);
  });
});
