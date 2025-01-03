import { Routes } from '@angular/router';
import { authGuard } from '@ng-matero/core';
import { AdminLayoutComponent } from '@ng-matero/theme/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from '@ng-matero/theme/auth-layout/auth-layout.component';
import { LoginComponent } from '@ng-matero/routes/sessions/login/login.component';
import { RegisterComponent } from '@ng-matero/routes/sessions/register/register.component';


export const routes: Routes = [
  {
    path: '',
    component: AdminLayoutComponent,
    canActivate: [authGuard],
    canActivateChild: [authGuard],
    children: [],
  },
  {
    path: 'auth',
    component: AuthLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
    ],
  },
  { path: '**', redirectTo: 'dashboard' },
];
