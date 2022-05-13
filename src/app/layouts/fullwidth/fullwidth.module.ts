import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FullwidthComponent } from './fullwidth.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from 'src/app/shared/shared.module';
import { LoginComponent } from 'src/app/modules/login/login.component';
import { WelcomePageComponent } from 'src/app/modules/welcome-page/welcome-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlreadyActivatedComponent } from 'src/app/modules/already-activated/already-activated.component';
import { ErrorComponent } from 'src/app/modules/error/error.component';
import { ExpiredTokenComponent } from 'src/app/modules/expired-token/expired-token.component';
import { SuccessefullyActivatedComponent } from 'src/app/modules/successefully-activated/successefully-activated.component';

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
