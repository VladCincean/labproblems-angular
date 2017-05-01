import {Component, Input} from '@angular/core';
import {Location} from '@angular/common';
import {Student} from "../shared/student.model";
import {StudentService} from "../shared/student.service";

@Component({
    moduleId: module.id,
    selector: 'ubb-student-new',
    templateUrl: './student-new.component.html',
    styleUrls: ['./student-new.component.css'],
})
export class StudentNewComponent {
    @Input() student: Student;

    constructor(private studentService: StudentService,
                private location: Location) {

    }

    goBack(): void {
        this.location.back();
    }

    save(serialNumber, name, studentGroup): void {
        console.log("student-new.component.ts::save(serialNumber={}, name={}, studentGroup={}", serialNumber, name, studentGroup);
        if (!this.isValid(serialNumber, name, studentGroup)) {
            console.log("all fields are required ");
            alert("all fields are required; studentGroup has to be an int");
        }
        this.studentService.create(serialNumber, name, studentGroup)
            .subscribe(_ => this.goBack());
    }

    private isValid(serialNumber, name, studentGroup) {
        if (!serialNumber || !name || !studentGroup) {
            console.log("all fields are required");
            return false;
        }
        if (!Number.isInteger(Number(studentGroup))) {
            console.log("studentGroup has to be an int");
            return false;
        }
        if ((studentGroup % 10 == 0) || ((studentGroup / 10) % 10 == 0) || studentGroup >= 1000 || studentGroup <= 110) {
            console.log("studentGroup describes an invalid group number");
            return false;
        }
        return true;

    }
}
