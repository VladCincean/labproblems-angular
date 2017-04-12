import {Injectable} from '@angular/core';
import {Http, Response} from "@angular/http";

import {Student} from "./student.model";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class StudentService {
  private studentsUrl = 'http://localhost:8080/api/students';

  constructor(private http: Http) {
  }

  getStudents(): Observable<Student[]> {
    return this.http.get(this.studentsUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.students || {};
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

  getStudent(id: number): Observable<Student> {
    return this.getStudents()
      .map(students => students.find(student => student.id === id));
  }

}
