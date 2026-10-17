import { Component } from '@angular/core';
import { RouterLink, RouterOutlet, RouterLinkActive } from '@angular/router';
import { MatTabsModule } from '@angular/material/tabs';
@Component({selector:'app-root',standalone:true,imports:[RouterOutlet,RouterLink,RouterLinkActive,MatTabsModule],template:`<main class="container"><h1>Student Management</h1><nav mat-tab-nav-bar [tabPanel]="tabPanel"><a mat-tab-link routerLink="/students" routerLinkActive #studentsActive="routerLinkActive" [active]="studentsActive.isActive">Students</a><a mat-tab-link routerLink="/courses" routerLinkActive #coursesActive="routerLinkActive" [active]="coursesActive.isActive">Courses</a></nav><mat-tab-nav-panel #tabPanel><router-outlet /></mat-tab-nav-panel></main>`,styles:[`.container{max-width:1100px;margin:40px auto;padding:0 20px}h1{font-size:28px;margin-bottom:24px}`]})
export class AppComponent {}
