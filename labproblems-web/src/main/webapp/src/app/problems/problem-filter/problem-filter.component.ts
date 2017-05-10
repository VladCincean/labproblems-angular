import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {Problem} from "../shared/problem.model";
import {ProblemService} from "../shared/problem.service";
import {Router} from "@angular/router";

@Component({
  selector: 'ubb-problem-filter',
  templateUrl: './problem-filter.component.html',
  styleUrls: ['./problem-filter.component.css']
})
export class ProblemFilterComponent implements OnInit {
  problems: Observable<Problem[]>;
  private searchTerms = new Subject<string>();

  constructor(
    private problemService: ProblemService,
    private router: Router
  ) { }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.problems = this.searchTerms
      .debounceTime(300)      // wait 300ms after each keystroke before considering the term
      .distinctUntilChanged() // ignore if next search term is same as previous
      .switchMap(term => term // switch to new observable each time the term changes
        // return the http search observable
        ? this.problemService.filter(term)
        // or the observable of empty heroes if there was no search term
        : Observable.of<Problem[]>([])
      )
      .catch(error => {
        console.log(error);
        return Observable.of<Problem[]>([]);
      });
  }

  gotoDetail(problem: Problem): void {
    let link = ['/problem/detail', problem.id];
    this.router.navigate(link);
  }

}
