import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {Student} from "./student.model";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class StudentService {
    private studentsUrl = 'http://localhost:8080/api/students';
    private headers = new Headers({'Content-Type': 'application/json'});

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
        console.log("Error (student.service.ts::handleError");
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

    private extractStudentData(res: Response) {
        let body = res.json();
        return body.student || {};
    }

    getStudent(id: number): Observable<Student> {
        return this.getStudents()
            .map(students => students.find(student => student.id === id));
    }

    create(serialNumber: string, name: string, studentGroup: number): Observable<Student> {
        let student = {serialNumber, name, studentGroup};
        return this.http
            .post(
                this.studentsUrl,
                JSON.stringify({"student": student}),
                {headers: this.headers}
            ).map(this.extractStudentData)
            .catch(this.handleError);
    }

    update(student): Observable<Student> {
        const url = `${this.studentsUrl}/${student.id}`;
        return this.http
            .put(
                url,
                JSON.stringify({"student": student}),
                {headers: this.headers}
            ).map(this.extractStudentData)
            .catch(this.handleError);
    }

    delete(id: number): Observable<void> {
        const url = `${this.studentsUrl}/${id}`;
        return this.http
          .delete(
            url,
            {headers: this.headers}
          ).map(() => null)
          .catch(this.handleError);
    }
}
