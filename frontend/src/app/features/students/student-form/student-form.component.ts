import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { CourseService } from '../../../core/services/course.service';
import { Course } from '../../../models/course.model';
import { Student } from '../../../models/student.model';
@Component({selector:'app-student-form',standalone:true,imports:[CommonModule,ReactiveFormsModule,MatDialogModule,MatFormFieldModule,MatInputModule,MatSelectModule,MatButtonModule],templateUrl:'./student-form.component.html',styles:[`.form{display:flex;flex-direction:column;gap:12px;padding-top:12px;min-width:300px}.error{color:#b91c1c}`]})
export class StudentFormComponent implements OnInit {
 form:FormGroup;courses:Course[]=[];loading=true;loadError='';
 constructor(fb:FormBuilder,private courseService:CourseService,public ref:MatDialogRef<StudentFormComponent>,@Inject(MAT_DIALOG_DATA) public data:Student|null){this.form=fb.group({name:[data?.name??'',[Validators.required,Validators.maxLength(100)]],email:[data?.email??'',[Validators.required,Validators.email]],courseIds:[data?.courseIds??[]]});}
 ngOnInit():void{this.courseService.getCourses().subscribe({next:cs=>{this.courses=cs;this.loading=false;},error:()=>{this.loadError='Failed to load courses';this.loading=false;}});}
 save():void{if(this.form.invalid||this.loading||this.loadError){this.form.markAllAsTouched();return;}this.ref.close(this.form.getRawValue());}
}
