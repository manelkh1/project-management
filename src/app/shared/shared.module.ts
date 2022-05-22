import { SidebarAdminComponent } from './sidebarAdmin/sidebar-admin.component';
import { SidebarMemberComponent } from './sidebarMember/sidebar-member.component';
import { SidebarBankComponent } from './sidebarBank/sidebar-bank.component';
import { SidebarManagerComponent } from './sidebarManager/sidebar-manager.component';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SnackbarComponent } from './snackbar/snackbar.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent,
    SidebarManagerComponent,
    SidebarBankComponent,
    SidebarMemberComponent,
    SidebarAdminComponent,
    FooterComponent,
    SnackbarComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    MatIconModule,
    FormsModule,
    MatButtonModule,
  ],
  exports: [
    HeaderComponent,
    SidebarComponent,
    SidebarManagerComponent,
    SidebarBankComponent,
    SidebarMemberComponent,
    SidebarAdminComponent,
    FooterComponent,
    SnackbarComponent,
  ],
})
export class SharedModule {}
