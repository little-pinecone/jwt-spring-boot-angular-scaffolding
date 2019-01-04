import { Component, OnInit } from '@angular/core';

import { Cookie } from '../../cookies/cookie';
import { CookieDataService } from '../../cookies/cookie-data.service';

@Component({
  selector: 'app-cookie-list',
  templateUrl: './cookie-list.component.html',
  styleUrls: ['./cookie-list.component.scss']
})
export class CookieListComponent implements OnInit {
   cookies: Cookie[];

  constructor(private cookieDataService: CookieDataService) { }

  ngOnInit() {
    this.getCookies();
  }

  private getCookies():void {
    this.cookieDataService.findAll()
    .subscribe(cookies => this.cookies = cookies);
  }
}
