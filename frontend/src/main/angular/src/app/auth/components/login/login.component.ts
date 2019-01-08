import { Component, OnInit } from '@angular/core';
import { Credentials } from '../../credentials';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  credentials: Credentials = new Credentials('', '');

  constructor() { }

  ngOnInit() {
  }

  public login(): void {
  }

}
