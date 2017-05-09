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
import { StudentNewComponent } from './students/student-new/student-new.component';
import { ProblemNewComponent } from './problems/problem-new/problem-new.component';
import { GradesComponent } from './grades/grades.component';
import {StudentProblemService} from "./grades/shared/student-problem.service";
import { ProblemAssignComponent } from './problem-assign/problem-assign.component';

@NgModule({
    declarations: [
        AppComponent,
        StudentDetailComponent,
        StudentsComponent,
        StudentListComponent,

        ProblemsComponent,
        ProblemListComponent,
        ProblemDetailComponent,
        StudentNewComponent,
        ProblemNewComponent,
        GradesComponent,
        ProblemAssignComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
    ],
    providers: [StudentService,ProblemService, StudentProblemService],
    bootstrap: [AppComponent]
})
export class AppModule {
}


