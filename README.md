# Github Repository Application
### Android App with Google Firebase Authentication, Fetching Github Repositories with Github API

### Tech Stack

Client: Android,Jetpack Compose, Kotlin <br />
Sever: Firebase <br />
Database: Room Db<br />
Authentication: Google SignIn API<br />

### Demo





https://github.com/user-attachments/assets/3fdbbf10-7ce1-4a2b-b075-994bb43b4c36




### Functionality of the application
The app allows users to enter a GitHub username and fetch all public repositories associated with <br/>
that account. It displays repository details such as name, description, and URL. The user can click on a<br/>
repo URL to view it in the browser. A Sign Out option is also available via the action bar, which logs the user<br/> 
out using Firebase Authentication and navigates back to the Auth module.

### What Componenets are used in the project?
- Jetpack Compose: For building declarative UI.<br/>

- Retrofit: For making API calls to GitHub’s REST API.<br/>

- Room: For local caching and persistence of fetched repositories.<br/>

- Firebase Authentication: For handling user authentication.<br/>

- MVVM + Clean Architecture: To separate concerns and structure the app.<br/>

- Modularization: The project is split into modules like main and auth for better scalability and maintainability.<br/>

 ### What architecture did you use and why do you think it’s best suitable?

 The app uses MVVM (Model-View-ViewModel) combined with Clean Architecture. MVVM makes it<br/>
 easy to manage UI-related data in a lifecycle-conscious way. Clean Architecture enforces<br/>
 separation of concerns by dividing the app into layers: Presentation, Domain, and Data. This<br/>
 makes the codebase modular, scalable, easier to test, and maintainable — especially as the project<br/>
 grows or teams collaborate.

 ### How do you ensure that code is testable?

Testability is ensured by:

- Using ViewModels to isolate UI logic, which can be tested independently. <br/>

- Applying the Repository Pattern to abstract data sources, making it easy to mock data for testing.<br/>

- Keeping UseCases in the domain layer to encapsulate business logic, which allows for unit testing without dependency on Android framework classes.<br/>

- Following modular design, so individual modules and components can be tested in isolation.<br/>

 

  
  
