import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Student, StudentRequest } from '../../models/student.model';
@Injectable({providedIn:'root'})
export class StudentService {
 private readonly url='http://localhost:8080/api/students';
 private subject=new BehaviorSubject<Student[]>([]);
 readonly students$=this.subject.asObservable();
 constructor(private http:HttpClient){}
 loadStudents():void{this.http.get<Student[]>(this.url).subscribe({next:data=>this.subject.next(data),error:e=>console.error('Failed to load students',e)});}
 getStudent(id:number):Observable<Student>{return this.http.get<Student>(`${this.url}/${id}`);}
 createStudent(req:StudentRequest):Observable<Student>{return this.http.post<Student>(this.url,req).pipe(tap(s=>this.subject.next([...this.subject.value,s])));}
 updateStudent(id:number,req:StudentRequest):Observable<Student>{return this.http.put<Student>(`${this.url}/${id}`,req).pipe(tap(s=>this.subject.next(this.subject.value.map(x=>x.id===id?s:x))));}
 deleteStudent(id:number):Observable<void>{return this.http.delete<void>(`${this.url}/${id}`).pipe(tap(()=>this.subject.next(this.subject.value.filter(x=>x.id!==id))));}
}
