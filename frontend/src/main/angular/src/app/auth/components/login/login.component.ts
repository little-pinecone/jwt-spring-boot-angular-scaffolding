import { Component, OnInit } from '@angular/core';
import { Credentials } from '../../credentials';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  credentials: Credentials = new Credentials('', '');

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  public login(): void {
    this.authService.login(this.credentials);
  }
}
