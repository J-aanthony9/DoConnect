import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AppComponent } from './app.component';
import { UserComponent } from './components/user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChatComponent } from './components/chat/chat.component';
import { AnswerComponent } from './components/answer/answer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CreateQuestionComponent } from './components/create-question/create-question.component';
import { SearchComponent } from './search/search.component';
import { PendingQuestionComponent } from './components/pending-question/pending-question.component';
import { PendingAnswerComponent } from './components/pending-answer/pending-answer.component';
import { CreateAnswerComponent } from './components/create-answer/create-answer.component';
import { ApproveAnswerComponent } from './components/approve-answer/approve-answer.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NoPageFoundComponent } from './no-page-found/no-page-found.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ChatComponent,
    AnswerComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    CreateQuestionComponent,
    SearchComponent,
    PendingQuestionComponent,
    PendingAnswerComponent,
    CreateAnswerComponent,
    ApproveAnswerComponent,
    HomeComponent,
    DashboardComponent,
    NoPageFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
