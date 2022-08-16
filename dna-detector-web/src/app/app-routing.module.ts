import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { MutantsComponent } from './pages/mutants/mutants.component';
import { NoMutantsComponent } from './pages/no-mutants/no-mutants.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  { path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  { path: 'login',  component: LoginComponent},
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard],
    children : [
      { path: 'mutants', component: MutantsComponent },
      { path: 'no-mutants', component: NoMutantsComponent },      
    ]
  },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
