import { AdminComponent } from './layouts/admin/admin.component';
import { HasRoleGuard } from './_helpers/has-role.guard';
import { MemberInvitationListComponent } from './modules/Member/invitations/member-invitation-list/member-invitation-list.component';
import { MemberComponent } from './layouts/member/member.component';
import { SendInvitationComponent } from './modules/Manager/invitation/send-invitation/send-invitation.component';
import { ManagerComponent } from './layouts/manager/manager.component';
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
import { ManagerInvitationListComponent } from './modules/Manager/invitation/invitation-list/invitation-list.component';
import { LoginComponent } from './modules/login/login.component';
import { PostComponent } from './modules/post/post.component';
import { CreateProjectComponent } from './modules/Manager/projects/create-project/create-project.component';
/* import { ProjectDetailsComponent } from './modules/manager/projects/project-details/project-details.component'; */
import { SuccessefullyActivatedComponent } from './modules/successefully-activated/successefully-activated.component';
import { UserDetailsComponent } from './modules/user-details/user-details/user-details.component';
import { WelcomePageComponent } from './modules/welcome-page/welcome-page.component';
import { AuthGuard } from './_helpers/auth.guard';
import { ManagerCreateAttachmentComponent } from './modules/Manager/attachment/create-attachment/create-attachment.component';
import { MemberCreateAttachmentComponent } from './modules/Member/attachment/member-create-attachment/member-create-attachment.component';
import { ProjectDetailsComponent } from './modules/Manager/projects/project-details/project-details.component';
import { UserProfilComponent } from './modules/user-profil/user-profil/user-profil.component';
import { HomeMemberComponent } from './modules/Member/home/home-member/home-member.component';
import { HomeManagerComponent } from './modules/Manager/home/home-manager/home-manager.component';
import { BankHomeComponent } from './modules/bank/home/bank-home/bank-home.component';
import { BankComponent } from './layouts/bank/bank.component';
import { InvitationListComponent } from './modules/invitation-list/invitation-list.component';
import { ProfilComponent } from './modules/Manager/profil/profil/profil.component';
//
const routes: Routes = [
  {
    path: 'default',
    component: DefaultComponent,
    /*  canActivate: [AuthGuard] ,*/

    children: [
      { path: 'home', component: HomeComponent /* canActivate: [AuthGuard] */ },

      { path: 'post', component: PostComponent /*canActivate: [AuthGuard]*/ },

      {
        path: 'create-project',
        component: CreateProjectComponent /*canActivate: [AuthGuard]*/,
      },

      {
        path: 'create-user',
        component: CreateUserComponent /* canActivate: [AuthGuard]*/,
      },
      {
        path: 'user-details/:id',
        component: UserDetailsComponent /* canActivate: [AuthGuard] */,
      },

      {
        path: 'invitation-list',
        component:
          ManagerInvitationListComponent /* canActivate: [AuthGuard]  */,
      },

      {
        path: 'change-password',
        component: ChangePasswordComponent /*canActivate: [AuthGuard]*/,
      },
      { path: 'chat', component: ChatComponent /*canActivate: [AuthGuard]*/ },
      {
        path: 'user-profil/:id',
        component: UserProfilComponent /*canActivate: [AuthGuard]*/,
      },
    ],
  },
  {
    path: 'admin',
    component: AdminComponent,
    /*canActivate: [AuthGuard, HasRoleGuard],*/
    data: {
      role: 'ADMIN',
    },

    children: [
      { path: 'home', component: HomeComponent /* canActivate: [AuthGuard] */ },

      {
        path: 'create-user',
        component: CreateUserComponent /* canActivate: [AuthGuard]*/,
      },
      {
        path: 'change-password',
        component: ChangePasswordComponent /*canActivate: [AuthGuard]*/,
      },

    ],
  },
  {
    path: 'manager',
    component: ManagerComponent,
    data: {
      role: 'MANAGER',
    },
    children: [
      { path: 'home', component: HomeManagerComponent /* canActivate: [AuthGuard] */ },
      { path: 'post', component: PostComponent /*canActivate: [AuthGuard]*/ },

      //

      {
        path: 'create-project',
        component: CreateProjectComponent /*canActivate: [AuthGuard]*/,
      },
      {
        path: 'project-details/:id',
        component: ProjectDetailsComponent /*canActivate: [AuthGuard]*/,
      },
      {
        path: 'profil',
        component: ProfilComponent /*canActivate: [AuthGuard]*/,
      },

      {
        path: 'create-attachment',
        component:
          ManagerCreateAttachmentComponent /* canActivate: [AuthGuard]  */,
      },
      {
        path: 'attachment-list',
        component:
          ManagerInvitationListComponent /* canActivate: [AuthGuard]  */,
      },
      {
        path: 'send-invitation',
        component: SendInvitationComponent /* canActivate: [AuthGuard]  */,
      },
      {
        path: 'invitation-list',
        component:
         InvitationListComponent /* canActivate: [AuthGuard]  */,
      },

      {
        path: 'change-password',
        component: ChangePasswordComponent /*canActivate: [AuthGuard]*/,
      }    ],
  },

  {
    path: 'member',
    component: MemberComponent,
    /* canActivate: [AuthGuard, HasRoleGuard],*/
    data: {
      role: 'MEMBER',
    },

    children: [
      { path: 'home', component: HomeMemberComponent /* canActivate: [AuthGuard] */ },
      { path: 'post', component: PostComponent /*canActivate: [AuthGuard]*/ },

      {
        path: 'project-details/:id',
        component: ProjectDetailsComponent /*canActivate: [AuthGuard]*/,
      },
      {
        path: 'attachment-list',
        component:
          MemberCreateAttachmentComponent /* canActivate: [AuthGuard]  */,
      },
      {
        path: 'invitation-list',
        component:
          MemberInvitationListComponent /* canActivate: [AuthGuard]  */,
      },

      {
        path: 'change-password',
        component: ChangePasswordComponent /*canActivate: [AuthGuard]*/,
      },
    ],
  },
  {
    path: 'bank',
    component: BankComponent,
    /*canActivate: [AuthGuard, HasRoleGuard],*/
    data: {
      role: 'BANK',
    },

    children: [
      { path: 'home', component: BankHomeComponent /* canActivate: [AuthGuard] */ },

      {
        path: 'change-password',
        component: ChangePasswordComponent /*canActivate: [AuthGuard]*/,
      },
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
