export class StudentProblem {

  studentId: number;
  problemId: number;
  problemTitle: string;
  grade: number;

  constructor(studentId: number, problemId: number, problemTitle: string, grade: number) {
    this.studentId = studentId;
    this.problemId = problemId;
    this.problemTitle = problemTitle;
    this.grade = grade;
  }
}
