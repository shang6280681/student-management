import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Course } from '../../../models/course.model';
@Component({selector:'app-course-form',standalone:true,imports:[CommonModule,ReactiveFormsModule,MatDialogModule,MatFormFieldModule,MatInputModule,MatButtonModule],templateUrl:'./course-form.component.html',styles:[`.form{display:flex;flex-direction:column;gap:12px;padding-top:12px;min-width:300px}`]})
export class CourseFormComponent {
 form:FormGroup;
 constructor(fb:FormBuilder,public ref:MatDialogRef<CourseFormComponent>,@Inject(MAT_DIALOG_DATA) public data:Course|null){this.form=fb.group({name:[data?.name??'',[Validators.required,Validators.maxLength(100)]],description:[data?.description??'',[Validators.maxLength(500)]]});}
 save():void{if(this.form.invalid){this.form.markAllAsTouched();return;}this.ref.close(this.form.getRawValue());}
}
