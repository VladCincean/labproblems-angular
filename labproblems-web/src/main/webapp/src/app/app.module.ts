import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {StudentService} from "./students/shared/student.service";
import {AppRoutingModule} from "./app-routing.module";
import {StudentDetailComponent} from "./students/student-detail/student-detail.component";
import {StudentsComponent} from "./students/students.component";
import {StudentListComponent} from "./students/student-list/student-list.component";
import {ProblemsComponent} from "./problems/problems.component";
import {ProblemListComponent} from "./problems/problem-list/problem-list.component";
import {ProblemService} from "./problems/shared/problem.service";
import {ProblemDetailComponent} from "./problems/problem-detail/problem-detail.component";

@NgModule({
    declarations: [
        AppComponent,
        StudentDetailComponent,
        StudentsComponent,
        StudentListComponent,

        ProblemsComponent,
        ProblemListComponent,
        ProblemDetailComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
    ],
    providers: [StudentService,ProblemService],
    bootstrap: [AppComponent]
})
export class AppModule {
}


