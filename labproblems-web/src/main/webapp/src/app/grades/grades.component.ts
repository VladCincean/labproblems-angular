import { Component, OnInit } from '@angular/core';
import {Student} from "../students/shared/student.model";
import {StudentService} from "../students/shared/student.service";
import {StudentProblem} from "./shared/student-problem.model";
import {StudentProblemService} from "./shared/student-problem.service";


@Component({
  moduleId: module.id,
  selector: 'ubb-grades',
  templateUrl: './grades.component.html',
  styleUrls: ['./grades.component.css'],
})

export class GradesComponent {
  errorMessage: string;
  showProblemsAndGrades: boolean;
  studentProblems: StudentProblem[];
  selectedStudent: Student;

  constructor(private studentService: StudentService,
              private studentProblemService: StudentProblemService,
              private location: Location) {

  }

  loadProblemsAndGrades(serialNumber: string) {
    this.showProblemsAndGrades = false;
    if (!serialNumber) {
      console.log("serial number must not be empty");
      alert("serial number must not be empty");
      return;
    }
    this.loadStudentProblemsForStudent(serialNumber);
  }

  private loadStudentProblemsForStudent(serialNumber: string) {
    this.studentService.getStudents()
      .subscribe(
        students => {
          const studentArr = students.filter(s => s.serialNumber === serialNumber);
          //TODO handle errors (studentArr should contain one student)
          if (studentArr && studentArr.length === 1) {
            this.showProblemsAndGrades = true;
            const student = studentArr[0];
            this.selectedStudent = student;
            this.studentProblemService.getStudentProblems(student.id)
              .subscribe(
                studentProblems => this.studentProblems = studentProblems,
                error => this.errorMessage = error)
          } else {
            console.log("studentArr ", studentArr);
          }
        },
        error => this.errorMessage = <any>error);
  }

  save(studentProblemForm) {
    let grades = studentProblemForm.form.value;
    console.log("grades: ", grades);
    this.studentProblemService.saveGrades(this.selectedStudent.id, grades)
      .subscribe(_ => this.goBack());
  }

  private goBack(): void {
   // #TODO solve error : this.location.back();
  }

}
