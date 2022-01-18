# Flight Information REST-API by SpringBoot+MyBatis

## Overview
1. [Techniques Involved](#Techniques-Involved)
2. [Implementation Details](#Implementation-Details)
3. [Endpoints Instruction](#Endpoints-Instruction)

## Techniques Involved
- Java 8
- Spring Boot
- MyBatis
- Maven
- MySQL/HSQLDB
- Spring Security OAuth 2.0

## Implementation Details
### 1. Database Storage Method
In consideration of the difficulty from deploying my database on 
online server, and thanks to this project just needs a little scale
data, I use HSQLDB (an in-memory database) to insert demo data. That
means all the changes in database will lose when you shut down the 
application program. It's more safe for the debugging.

### 2. Security Method
This project uses resource owner password credentials in OAuth2.0 as 
authentication method, so the access authorization process is:
- First, request access token by username and password from server.
- Second, get access token and hold it until access token expired.
- Third, request the resource by access token during validity period.

That is a valid user: {username: zth, password: 123456}

### 3. Testing Method
As the most of Spring Boot API, you can test it by Curl command or 
Postman, you can find Postman here: https://www.postman.com

## Endpoints Instruction
Notes: Except the register, all endpoints are encrypted. Assuming that
the program is running on local machine.
### 1. User Endpoints
- UserRegister(POST) http://localhost:8080/user/register
  - Body e.g. {"id": x, "username": "xxx", "password": "xxx"}
- UserList(GET) http://localhost:8080/user/list

### 2. Flight Endpoints
- Add(POST) http://localhost:8080/flight
- Remove(DELETE) http://localhost:8080/flight/1
- Update(PUT) http://localhost:8080/flight/1
  - Body e.g. {
    "id": 1,
    "flight_num": 1,
    "name": "SCANDINAVIAN AIRLINES",
    "s_date": "2020-06-06",
    "a_date": "2020-06-06",
    "departure": "STOCKHOLM",
    "destination": "BEIJING",
    "fare": 1200.0,
    "duration": 5.0
    }
- Search(GET)
  - By id: http://localhost:8080/flight/id/1
  - By name: http://localhost:8080/flight/name/SCANDINAVIAN AIRLINES
  - By scheduled date: http://localhost:8080/flight/date/2020-06-06
  - By departure & destination: http://localhost:8080/flight/route/BEIJING/STOCKHOLM
  - List all: http://localhost:8080/flight
- Paginated List all: http://localhost:8080/flight/paginated/1
