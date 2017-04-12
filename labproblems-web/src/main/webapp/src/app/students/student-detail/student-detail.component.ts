import {Component, OnInit, Input} from '@angular/core';
import {Student} from "../shared/student.model";
import {StudentService} from "../shared/student.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';

import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  @Input()
  student: Student;

  constructor(private studentService: StudentService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.studentService.getStudent(+params['id']))
      .subscribe(student => this.student = student);
  }

  goBack(): void {
    this.location.back();
  }

}
