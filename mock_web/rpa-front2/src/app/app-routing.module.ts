import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ManageAccountComponent } from './modules/manageaccount/components/manage-account/manage-account.component';
import { HomeComponent } from './modules/manageaccount/components/home/home.component';
import { LoginComponent } from './modules/home/components/login/login.component';
import { ErrorComponent } from './shared/pages/error/error.component';
import { CreateMultipleAccountComponent } from './modules/manageaccount/components/create-multiple-account/create-multiple-account.component';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full',  },
  { path: 'error', component: ErrorComponent },
  { path: 'login', component: LoginComponent },
  { path: 'manage-account', component: HomeComponent },
  { path: 'manage-account/manage-account', component: ManageAccountComponent },
  { path: 'manage-account/manage-multiple-account', component: CreateMultipleAccountComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
