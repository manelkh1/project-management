import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FullwidthComponent } from './fullwidth.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from '../../shared/shared.module';
import { LoginComponent } from '../../modules/login/login.component';
import { WelcomePageComponent } from '../../modules/welcome-page/welcome-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlreadyActivatedComponent } from '../../modules/already-activated/already-activated.component';
import { ErrorComponent } from '../../modules/error/error.component';
import { ExpiredTokenComponent } from '../../modules/expired-token/expired-token.component';
import { SuccessefullyActivatedComponent } from '../../modules/successefully-activated/successefully-activated.component';

@NgModule({
  declarations: [
    FullwidthComponent,
    LoginComponent,
    WelcomePageComponent,
    ExpiredTokenComponent,
    SuccessefullyActivatedComponent,
    AlreadyActivatedComponent,
    ErrorComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class FullwidthModule {}
