import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cookie } from './cookie';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class CookieDataService {

  private cookiesUrl = API_URL + '/cookies';

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Cookie[]> {
    return this.http.get<Cookie[]>(this.cookiesUrl, httpOptions);
  }

}
