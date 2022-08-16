import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { MutantsComponent } from './pages/mutants/mutants.component';
import { NoMutantsComponent } from './pages/no-mutants/no-mutants.component';
import { JwtModule } from '@auth0/angular-jwt';
import { HumanCardComponent } from './pages/human-card/human-card.component';
import { AuthInterceptorService } from './auth/auth-interceptor.service';

export function tokenGetter() {
  return localStorage.getItem('ACCESS_TOKEN');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    MutantsComponent,
    NoMutantsComponent,    
    HumanCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter
      }
    })
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
