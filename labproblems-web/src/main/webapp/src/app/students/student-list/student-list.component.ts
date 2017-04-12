import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Student} from "../shared/student.model";
import {StudentService} from "../shared/student.service";

@Component({
  moduleId: module.id,
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  errorMessage: string;
  students: Student[];
  selectedStudent: Student;

  constructor(private studentService: StudentService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getStudents();
  }

  getStudents() {
    this.studentService.getStudents()
      .subscribe(
        students => this.students = students,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(student: Student): void {
    this.selectedStudent = student;
  }

  gotoDetail(): void {
    this.router.navigate(['/student/detail', this.selectedStudent.id]);
  }


}
