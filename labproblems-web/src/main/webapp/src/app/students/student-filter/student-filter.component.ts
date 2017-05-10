import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {Student} from "../shared/student.model";
import {StudentService} from "../shared/student.service";
import {Router} from "@angular/router";



@Component({
  selector: 'ubb-student-filter',
  templateUrl: './student-filter.component.html',
  styleUrls: ['./student-filter.component.css']
})
export class StudentFilterComponent implements OnInit {
  students: Observable<Student[]>;
  private searchTerms = new Subject<string>();

  constructor(
    private studentService: StudentService,
    private router:Router
  ) { }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.students = this.searchTerms
      .debounceTime(300)      // wait 300ms after each keystroke before considering the term
      .distinctUntilChanged() // ignore if next search term is same as previous
      .switchMap(term => term // switch to new observable each time the term changes
          // return the http search observable
      ? this.studentService.filter(term)
          // or the observable of empty heroes if there was no search term
      : Observable.of<Student[]>([])
      )
      .catch(error => {
        console.log(error);
        return Observable.of<Student[]>([]);
      });
  }

  gotoDetail(student: Student): void {
    let link = ['/student/detail', student.id];
    this.router.navigate(link);
  }
}
