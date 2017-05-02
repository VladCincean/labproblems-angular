import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {StudentDetailComponent} from "./students/student-detail/student-detail.component";
import {StudentsComponent} from "./students/students.component";
import {ProblemsComponent} from "./problems/problems.component";
import {ProblemDetailComponent} from "./problems/problem-detail/problem-detail.component";
import {StudentNewComponent} from "./students/student-new/student-new.component";
import {ProblemNewComponent} from "./problems/problem-new/problem-new.component";


const routes: Routes = [
    // { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'students',     component: StudentsComponent },
    { path: 'student/detail/:id', component: StudentDetailComponent},
    { path: 'student/new', component: StudentNewComponent},

    { path: 'problems',     component: ProblemsComponent },
    { path: 'problem/detail/:id', component: ProblemDetailComponent},
    { path: 'problem/new', component: ProblemNewComponent},
];
@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
