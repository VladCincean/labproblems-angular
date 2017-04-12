import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { StudentsComponent } from './students/students.component';
import { ProblemsComponent } from './problems/problems.component';
import { StudentDetailComponent } from './students/student-detail/student-detail.component';
import { StudentListComponent } from './students/student-list/student-list.component';
import { ProblemDetailComponent } from './problems/problem-detail/problem-detail.component';
import { ProblemListComponent } from './problems/problem-list/problem-list.component';
import {AppRoutingModule} from "./app-routing.module";

@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    ProblemsComponent,
    StudentDetailComponent,
    StudentListComponent,
    ProblemDetailComponent,
    ProblemListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
