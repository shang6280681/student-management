import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { CourseService } from '../../../core/services/course.service';
import { StudentService } from '../../../core/services/student.service';
import { Course, CourseRequest } from '../../../models/course.model';
import { CourseFormComponent } from '../course-form/course-form.component';
import { ConfirmDialogComponent } from '../../../shared/confirm-dialog/confirm-dialog.component';
@Component({selector:'app-course-list',standalone:true,imports:[CommonModule,MatTableModule,MatButtonModule,MatDialogModule,MatSnackBarModule],templateUrl:'./course-list.component.html',styles:[`.header{display:flex;justify-content:space-between;align-items:center;margin-bottom:20px}table{width:100%}th,td{padding:12px}`]})
export class CourseListComponent implements OnInit {
 courses$:Observable<Course[]>;displayedColumns=['name','description','actions'];
 constructor(private service:CourseService,private students:StudentService,private dialog:MatDialog,private snack:MatSnackBar){this.courses$=service.courses$;}
 ngOnInit():void{this.service.loadCourses();}
 add():void{this.dialog.open(CourseFormComponent,{width:'450px',data:null}).afterClosed().subscribe((req:CourseRequest|undefined)=>{if(req)this.service.createCourse(req).subscribe({next:()=>this.message('Course created'),error:e=>this.message(e.error?.error||'Failed to create course')});});}
 edit(c:Course):void{this.dialog.open(CourseFormComponent,{width:'450px',data:c}).afterClosed().subscribe((req:CourseRequest|undefined)=>{if(req)this.service.updateCourse(c.id,req).subscribe({next:()=>this.message('Course updated'),error:e=>this.message(e.error?.error||'Failed to update course')});});}
 remove(c:Course):void{this.dialog.open(ConfirmDialogComponent,{width:'420px',data:{message:`Delete ${c.name}? Student enrollments will be removed.`}}).afterClosed().subscribe(ok=>{if(ok===true)this.service.deleteCourse(c.id).subscribe({next:()=>{this.message('Course deleted');this.students.loadStudents();},error:e=>this.message(e.error?.error||'Failed to delete course')});});}
 private message(m:string):void{this.snack.open(m,'Close',{duration:3000});}
}
