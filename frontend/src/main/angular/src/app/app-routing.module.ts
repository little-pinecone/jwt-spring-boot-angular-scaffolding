import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './auth/guards/auth.guard';
import { LoginComponent } from './auth/components/login/login.component';
import { CookieListComponent } from './pages/cookie-list/cookie-list.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { canActivate: [AuthGuard], path: 'cookies', component: CookieListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
