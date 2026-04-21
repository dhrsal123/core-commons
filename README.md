# Cinema Core Commons
Shared library containing domain POJOs, Enums, and Exception handling logic.
## Quick Start
This module provides a unified way to handle errors across the Cinema API ecosystem.
### Exception Structure
All custom exceptions should extend `CinemaException` and utilize the `CinemaExceptionTypes` catalog.
To create a new exception, do so as follows:
```java
throw new CinemaException("User already registered", CinemaExceptionTypes.USER_ALREADY_EXIST);
```
## Stack
- Java 21
- Lombok
- Spring Boot Starter Web
- JUnit 5 & AssertJ