import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SnackbarComponent } from './snackbar/snackbar.component';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    SnackbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    MatIconModule,
    MatButtonModule
  ],
  exports:[
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    SnackbarComponent
  ]
})
export class SharedModule { }
