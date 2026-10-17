import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { StudentService } from '../../../core/services/student.service';
import { CourseService } from '../../../core/services/course.service';
import { Student, StudentRequest } from '../../../models/student.model';
import { Course } from '../../../models/course.model';
import { StudentFormComponent } from '../student-form/student-form.component';
import { ConfirmDialogComponent } from '../../../shared/confirm-dialog/confirm-dialog.component';
@Component({selector:'app-student-list',standalone:true,imports:[CommonModule,MatTableModule,MatButtonModule,MatDialogModule,MatSnackBarModule],templateUrl:'./student-list.component.html',styles:[`.header{display:flex;justify-content:space-between;align-items:center;margin-bottom:20px}table{width:100%}th,td{padding:12px}`]})
export class StudentListComponent implements OnInit {
 students$:Observable<Student[]>;courses:Course[]=[];displayedColumns=['name','email','courses','actions'];
 constructor(private service:StudentService,private courseService:CourseService,private dialog:MatDialog,private snack:MatSnackBar){this.students$=service.students$;}
 ngOnInit():void{this.service.loadStudents();this.courseService.getCourses().subscribe({next:cs=>this.courses=cs,error:()=>this.message('Failed to load courses')});}
 courseNames(ids:number[]):string{return ids.map(id=>this.courses.find(c=>c.id===id)?.name).filter((x):x is string=>!!x).join(', ')||'None';}
 add():void{this.dialog.open(StudentFormComponent,{width:'450px',data:null}).afterClosed().subscribe((req:StudentRequest|undefined)=>{if(req)this.service.createStudent(req).subscribe({next:()=>this.message('Student created'),error:e=>this.message(e.error?.error||'Failed to create student')});});}
 edit(s:Student):void{this.dialog.open(StudentFormComponent,{width:'450px',data:s}).afterClosed().subscribe((req:StudentRequest|undefined)=>{if(req)this.service.updateStudent(s.id,req).subscribe({next:()=>this.message('Student updated'),error:e=>this.message(e.error?.error||'Failed to update student')});});}
 remove(s:Student):void{this.dialog.open(ConfirmDialogComponent,{width:'400px',data:{message:`Delete ${s.name}?`}}).afterClosed().subscribe(ok=>{if(ok===true)this.service.deleteStudent(s.id).subscribe({next:()=>this.message('Student deleted'),error:e=>this.message(e.error?.error||'Failed to delete student')});});}
 private message(m:string):void{this.snack.open(m,'Close',{duration:3000});}
}
