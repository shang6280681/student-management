import { Routes } from '@angular/router';
import { StudentListComponent } from './features/students/student-list/student-list.component';
import { CourseListComponent } from './features/courses/course-list/course-list.component';
export const routes:Routes=[{path:'',redirectTo:'students',pathMatch:'full'},{path:'students',component:StudentListComponent},{path:'courses',component:CourseListComponent},{path:'**',redirectTo:'students'}];
