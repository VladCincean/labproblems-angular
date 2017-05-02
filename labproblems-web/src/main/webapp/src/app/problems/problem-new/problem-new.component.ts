import {Component, Input} from '@angular/core';
import {Location} from '@angular/common';
import {Problem} from "../shared/problem.model";
import {ProblemService} from "../shared/problem.service";

@Component({
  moduleId: module.id,
  selector: 'ubb-problem-new',
  templateUrl: './problem-new.component.html',
  styleUrls: ['./problem-new.component.css'],
})
export class ProblemNewComponent {
  @Input() problem: Problem;

  constructor(private problemService: ProblemService,
              private location: Location) {

  }

  goBack(): void {
    this.location.back();
  }

  save(title, description): void {
    console.log("problem-new.component.ts::save(title=", title, "description=", description,  ")");
    if (!this.isValid(title,description)) {
      console.log("all fields are required ");
      alert("all fields are required");
    }
    this.problemService.create(title, description)
      .subscribe(_ => this.goBack());
  }

  private isValid(title, description) {
    if (!title || !description) {
      console.log("all fields are required");
      return false;
    }
    return true;

  }

}
