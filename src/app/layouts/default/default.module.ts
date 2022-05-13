// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { HomeComponent } from 'src/app/modules/home/home.component';
// import { PostComponent } from 'src/app/modules/post/post.component';
// import { RouterModule } from '@angular/router';
// import { SharedModule } from 'src/app/shared/shared.module';
// import { FlexLayoutModule } from '@angular/flex-layout';
// import { CreateProjectComponent } from 'src/app/modules/projects/create-project/create-project.component';
// import { ProjectDetailsComponent } from 'src/app/modules/projects/project-details/project-details.component';
// import { CreateUserComponent } from 'src/app/modules/create-user/create-user.component';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import {MatSnackBarModule} from '@angular/material/snack-bar';
// import {MatDatepickerModule} from '@angular/material/datepicker';
// import {MatInputModule} from '@angular/material/input';
// import { MatNativeDateModule } from '@angular/material/core';
// import { MatFormFieldModule } from '@angular/material/form-field';
// import {MatTableModule} from '@angular/material/table';
// import {MatAutocompleteModule} from '@angular/material/autocomplete';




// @NgModule({
//   declarations: [
//     HomeComponent,
//     PostComponent,
//     ProjectDetailsComponent, 
//     CreateProjectComponent,
//     CreateUserComponent
//   ],
//   imports: [
//     CommonModule,
//     RouterModule,
//     SharedModule,
//     FlexLayoutModule,
//     FormsModule,
//     ReactiveFormsModule,
//     MatSnackBarModule,
//     MatDatepickerModule,
//     MatInputModule,
//     MatNativeDateModule,
//     MatFormFieldModule,
//     MatTableModule,
//     MatAutocompleteModule
//   ]
// })
// export class DefaultModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DefaultComponent } from './default.component';
import { HomeComponent } from 'src/app/modules/home/home.component';
import { PostComponent } from 'src/app/modules/post/post.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/shared/shared.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CreateProjectComponent } from 'src/app/modules/projects/create-project/create-project.component';
import { ProjectDetailsComponent } from 'src/app/modules/projects/project-details/project-details.component';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {A11yModule} from '@angular/cdk/a11y';
import {CdkAccordionModule} from '@angular/cdk/accordion';
import {ClipboardModule} from '@angular/cdk/clipboard';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {PortalModule} from '@angular/cdk/portal';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {CdkStepperModule} from '@angular/cdk/stepper';
import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatBadgeModule} from '@angular/material/badge';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatChipsModule} from '@angular/material/chips';
import {MatStepperModule} from '@angular/material/stepper';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSliderModule} from '@angular/material/slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSortModule} from '@angular/material/sort';
import {MatTabsModule} from '@angular/material/tabs';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTreeModule} from '@angular/material/tree';
import {OverlayModule} from '@angular/cdk/overlay';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateUserComponent } from 'src/app/modules/create-user/create-user.component';
import { UserDetailsComponent } from 'src/app/modules/user-details/user-details/user-details.component';
import { InvitationListComponent } from 'src/app/modules/invitation-list/invitation-list.component';
import { ChangePasswordComponent } from 'src/app/modules/change-password/change-password.component';
import { ChatComponent } from 'src/app/modules/chat/chat.component';


@NgModule({
  declarations: [
    DefaultComponent,
    HomeComponent,
    PostComponent,
    ProjectDetailsComponent, 
    CreateProjectComponent,
    HomeComponent,
   CreateUserComponent,
   UserDetailsComponent,
   InvitationListComponent,
   ChangePasswordComponent,
   ChatComponent
  ],
  imports: [
    CommonModule,
     RouterModule,
   FormsModule,
     ReactiveFormsModule,
     MatSnackBarModule,
     MatDatepickerModule,
     MatInputModule,
     MatNativeDateModule,
     MatTableModule,
    SharedModule,
    FlexLayoutModule,
    MatFormFieldModule,
    A11yModule,
    CdkAccordionModule,
    ClipboardModule,
    CdkStepperModule,
    CdkTableModule,
    CdkTreeModule,
    DragDropModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    OverlayModule,
    PortalModule,
    ScrollingModule,
    
  ]
})
export class DefaultModule { }
