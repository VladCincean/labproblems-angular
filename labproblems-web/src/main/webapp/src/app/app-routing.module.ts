import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {StudentDetailComponent} from "./students/student-detail/student-detail.component";
import {StudentsComponent} from "./students/students.component";
import {ProblemsComponent} from "./problems/problems.component";
import {ProblemDetailComponent} from "./problems/problem-detail/problem-detail.component";
import {StudentNewComponent} from "./students/student-new/student-new.component";
import {ProblemNewComponent} from "./problems/problem-new/problem-new.component";
import {GradesComponent} from "./grades/grades.component";
import {ProblemAssignComponent} from "./problem-assign/problem-assign.component";


const routes: Routes = [
    // { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'grades',     component: GradesComponent },
    { path: 'students',     component: StudentsComponent },
    { path: 'student/detail/:id', component: StudentDetailComponent},
    { path: 'student/new', component: StudentNewComponent},
    { path: 'assign', component: ProblemAssignComponent},
    { path: 'problem/detail/:id', component: ProblemDetailComponent},
    { path: 'problem/new', component: ProblemNewComponent},
    { path: 'problems',     component: ProblemsComponent },


];
@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
