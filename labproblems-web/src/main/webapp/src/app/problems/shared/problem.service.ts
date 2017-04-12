import {Injectable} from '@angular/core';
import {Http, Response} from "@angular/http";

import {Problem} from "./problem.model";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class ProblemService {
  private problemsUrl = 'http://localhost:8080/api/problems';

  constructor(private http: Http) {
  }

  getProblems(): Observable<Problem[]> {
    return this.http.get(this.problemsUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.problems || {};
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

  getProblem(id: number): Observable<Problem> {
    return this.getProblems()
      .map(problems => problems.find(problem => problem.id === id));
  }

}
