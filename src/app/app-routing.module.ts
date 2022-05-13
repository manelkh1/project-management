import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DefaultComponent } from './layouts/default/default.component';
import { FullwidthComponent } from './layouts/fullwidth/fullwidth.component';
import { AlreadyActivatedComponent } from './modules/already-activated/already-activated.component';
import { ChangePasswordComponent } from './modules/change-password/change-password.component';
import { ChatComponent } from './modules/chat/chat.component';
import { CreateUserComponent } from './modules/create-user/create-user.component';
import { ErrorComponent } from './modules/error/error.component';
import { ExpiredTokenComponent } from './modules/expired-token/expired-token.component';
import { HomeComponent } from './modules/home/home.component';
import { InvitationListComponent } from './modules/invitation-list/invitation-list.component';
import { LoginComponent } from './modules/login/login.component';
import { PostComponent } from './modules/post/post.component';
import { CreateProjectComponent } from './modules/projects/create-project/create-project.component';
import { ProjectDetailsComponent } from './modules/projects/project-details/project-details.component';
import { SuccessefullyActivatedComponent } from './modules/successefully-activated/successefully-activated.component';
import { UserDetailsComponent } from './modules/user-details/user-details/user-details.component';
import { WelcomePageComponent } from './modules/welcome-page/welcome-page.component';
import { AuthGuard } from './_helpers/auth.guard';

const routes: Routes = [
  {
    path: 'default',
    component: DefaultComponent,
    canActivate: [AuthGuard],

    children: [
      { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
      { path: 'post', component: PostComponent, canActivate: [AuthGuard] },
      { path: 'create-project', component: CreateProjectComponent, canActivate: [AuthGuard] },
      { path: 'project-details/:id', component: ProjectDetailsComponent, canActivate: [AuthGuard] },
      { path: 'create-user', component: CreateUserComponent, canActivate: [AuthGuard] },
      { path: 'user-details/:id', component: UserDetailsComponent, canActivate: [AuthGuard] },
      { path: 'invitation-list', component: InvitationListComponent, canActivate: [AuthGuard] },
      {path:'change-password', component: ChangePasswordComponent,canActivate: [AuthGuard]  } ,
      {path:'chat', component: ChatComponent,canActivate: [AuthGuard]  } 
    ],
  },
  {
    path: '',
    component: FullwidthComponent,
    children: [
      { path: '', component: WelcomePageComponent },
      { path: 'login', component: LoginComponent },
      { path: 'error-404', component: ErrorComponent },
      { path: 'expired-token', component: ExpiredTokenComponent },
      {
        path: 'successefully-activated',
        component: SuccessefullyActivatedComponent,
      },
      { path: 'already-activated', component: AlreadyActivatedComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
