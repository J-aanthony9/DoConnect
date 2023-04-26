import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { CreateQuestionComponent } from './components/create-question/create-question.component';
import { SearchComponent } from './search/search.component';
import { PendingQuestionComponent } from './components/pending-question/pending-question.component';

const routes: Routes = [
  {path: '', redirectTo:'login',pathMatch:'full'},
  {path: 'register', component:RegisterComponent},
  {path: 'login', component:LoginComponent},
  {path: 'create_question', component:CreateQuestionComponent},
  {path: 'search', component:SearchComponent},
  {path: 'pending_question', component:PendingQuestionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
