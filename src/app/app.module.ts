import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FullwidthModule } from './layouts/fullwidth/fullwidth.module';
import { AuthGuard } from './_helpers/auth.guard';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { DefaultModule } from './layouts/default/default.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';


@NgModule({
  declarations: [AppComponent, ],
  imports: [
    RouterModule,
    BrowserModule,
    AppRoutingModule,
    FullwidthModule,
    DefaultModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatButtonModule,
    MatDatepickerModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    
  ],
  providers: [authInterceptorProviders, AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule {}
