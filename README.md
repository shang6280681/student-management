# Student Management

Full-stack Student and Course CRUD application using Spring Boot 3, Java 17, Angular 18, Angular Material, RxJS, and an in-memory H2 database.

## Structure

* `backend/` Spring Boot REST API, JPA entities, validation, transactions, exception handling, JUnit/Mockito tests.
* `frontend/` Angular 18 standalone application, Material tabs/tables/dialogs, reactive forms, RxJS BehaviorSubject.

## Requirements

* JDK 17 or 21
* Maven 3.9+ (or import `backend/pom.xml` into Eclipse)
* Node.js compatible with Angular 18 (Node 20.11.1+ within Node 20 is recommended)
* npm

## Start backend

(1) right click /student-management/src/main/java/com/example/studentmanagement/StudentManagementApplication.java, and Run As -> java application
OR

(2)```bash
cd backend
mvn test
mvn spring-boot:run

```

Backend: http://localhost:8080

H2 console: http://localhost:8080/h2-console — JDBC URL `jdbc:h2:mem:studentdb`, user `sa`, blank password.

## Start Frontend 

go to eclipse terminal 
cd frontend
npm install
npm start
```

Frontend: http://localhost:4200

Build: `npm run build`

## REST endpoints

|Method|Path|Action|
|-|-|-|
|GET|`/api/students`|List students|
|GET|`/api/students/{id}`|Get student|
|POST|`/api/students`|Create student|
|PUT|`/api/students/{id}`|Update student|
|DELETE|`/api/students/{id}`|Delete student|
|GET|`/api/courses`|List courses|
|GET|`/api/courses/{id}`|Get course|
|POST|`/api/courses`|Create course|
|PUT|`/api/courses/{id}`|Update course|
|DELETE|`/api/courses/{id}`|Delete course|

Create courses before enrolling students. Example student payload:

```json
{"name":"John Smith","email":"john@example.com","courseIds":\[1,2]}
```

## Notes

* Course deletion removes student-course join records but preserves students.
* HTTP error responses use `{ "error": "..." }`.
* This ZIP is a reconstructed project from the conversation, **not a backup of files on your computer**.
* `RestTemplate` integration is implemented as a **separate, optional external HTTP demo**. It is not used for Student/Course CRUD.

## RestTemplate external API demo

The backend uses a Spring-managed `RestTemplate` with connection/read timeouts to fetch a **public sample user** from JSONPlaceholder. This demonstrates an outbound REST call and JSON-to-Java record mapping without transmitting any student data.

* `GET /api/external/demo-users/1` → JSON `{ "id": 1, "name": "Leanne Graham", "email": "Sincere@april.biz" }` (sample response; the upstream API controls the content)
* `GET /api/external/demo-users/999` → 404 when no demo user exists
* Invalid IDs → 400; external service/network failures → 502
* Base URL configured via `external.users.base-url` in `application.properties`
* Unit tests use `MockRestServiceServer`, so they require no internet connection.

This feature is intentionally separate from locally stored Students: public demo users are **not** imported or saved to H2. Runtime access to the external endpoint requires internet connectivity.

* Verify `mvn test` and `npm run build` locally before submission.

