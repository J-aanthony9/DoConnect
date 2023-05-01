import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { CreateQuestionComponent } from './components/create-question/create-question.component';
import { SearchComponent } from './search/search.component';
import { PendingQuestionComponent } from './components/pending-question/pending-question.component';
import { PendingAnswerComponent } from './components/pending-answer/pending-answer.component';
import { CreateAnswerComponent } from './components/create-answer/create-answer.component';
import { ApproveAnswerComponent } from './components/approve-answer/approve-answer.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NoPageFoundComponent } from './no-page-found/no-page-found.component';
import { ChatComponent } from './components/chat/chat.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'create_question', component: CreateQuestionComponent },
  { path: 'search', component: SearchComponent },
  { path: 'pending_question', component: PendingQuestionComponent },
  { path: 'pending_answer', component: PendingAnswerComponent },
  { path: 'create_answer', component: CreateAnswerComponent },
  {path: 'create_answer/:id',component: CreateAnswerComponent},
  { path: 'approve_answer', component: ApproveAnswerComponent },
  { path: 'home', component: HomeComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'chat', component: ChatComponent },
  { path: '**', component: NoPageFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
