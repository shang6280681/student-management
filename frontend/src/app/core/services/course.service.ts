import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Course, CourseRequest } from '../../models/course.model';
@Injectable({providedIn:'root'})
export class CourseService {
 private readonly url='http://localhost:8080/api/courses';
 private subject=new BehaviorSubject<Course[]>([]);
 readonly courses$=this.subject.asObservable();
 constructor(private http:HttpClient){}
 loadCourses():void{this.http.get<Course[]>(this.url).subscribe({next:data=>this.subject.next(data),error:e=>console.error('Failed to load courses',e)});}
 getCourses():Observable<Course[]>{return this.http.get<Course[]>(this.url);}
 getCourse(id:number):Observable<Course>{return this.http.get<Course>(`${this.url}/${id}`);}
 createCourse(req:CourseRequest):Observable<Course>{return this.http.post<Course>(this.url,req).pipe(tap(c=>this.subject.next([...this.subject.value,c])));}
 updateCourse(id:number,req:CourseRequest):Observable<Course>{return this.http.put<Course>(`${this.url}/${id}`,req).pipe(tap(c=>this.subject.next(this.subject.value.map(x=>x.id===id?c:x))));}
 deleteCourse(id:number):Observable<void>{return this.http.delete<void>(`${this.url}/${id}`).pipe(tap(()=>this.subject.next(this.subject.value.filter(x=>x.id!==id))));}
}
