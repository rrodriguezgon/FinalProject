

# Final Project: PadelFriendsApp

<p align="center"><strong>Raquel Rodriguez Gonzalez</strong></p>

## Index
* [1. Introduction](#introduction)
* [2. Tools](#tools)
* [4. Microservices Structure](#microservices-structure)
  * [4.1. Deploy](#deploy)
* [5. Documentation](#documentation)  
  * [5.1. Swagger](#swagger)
  * [5.2. Postman](#Postman)
* [6. Test Coverage](#test-coverage)
* [7. Front End](#front-end)
  * [7.1. New Framework Implementation](#new-framework-implementation)  
  * [7.2. Credentials](#credentials)
* [8. Additional comment](#Additional-comment) 


## Introduction
 
PadelFriends is designed to be a tool with which all amateur or professional padel players can organize their matches with their friends, create groups and thus notify and inform their friends of the reservations made.

## Tools
- IntelliJ (Compile and run Java Program, JDK 11).
- Visual Studio Code.
- Spring Boot Dependencies.
- MySQL.
- MongoDB.
- Postman.
- Swagger (API Document with HTML).
- Drawio (Draw Microservice Structure Diagram).
- Angular (including HTML, CSS and TypeScript) to create the front-end of the app.
- Bootstrap Framework css.
- ng-Bootstrap plugin.
- **New Framework**: MapBox Library.
- Heroku to deploy the api microservices in Cloud.
- Firebase to deploy the app Angular in Cloud.
- Mysql database hosted on our own server.
- MongoDB hosted on MongoAtlas.

## Microservices Structure

<p align="center"><img width="700" height="700" src="https://i.ibb.co/StznBDL/Untitled-Diagram-1.png"></p>

The application is built with **7** different microservices:
- **Eureka Server**: In which relies the whole application.
- **Padel Friends Edge Service**: The user interface communicates directly with this microservice which distributes the respective functionality to the other microservices.
- **User Service**: Is associated with a **MySql database** and responsible to make the CRUD (Create-read-update-delete) requests about the Users.
- **Group Service**: Is associated with a **MySql database** and responsible to make the CRUD (Create-read-update-delete) requests about the Groups.
- **Reservation Service**: Is associated with a **MySql database** and responsible to make the CRUD (Create-read-update-delete) requests about the Reservations.
- **Club Service**: Is associated with a **MySql database** and responsible to make the CRUD (Create-read-update-delete) requests about the Clubs.
- **Relations Service**: Is associated with a **Mongo database** and responsible to make the CRUD (Create-read-update-delete) requests about the Relations.

### Deploy

All application Microservices were **deployed in cloud**, using **Heroku**. Down bellow, there are all links in which the microservices are lifted.

* [Club Service](https://clubservice.herokuapp.com/)
* [Group Service](https://groupservice.herokuapp.com/)
* [Relations Service](https://relationservice.herokuapp.com/)
* [Reservations Service](https://reservationsservice.herokuapp.com/)
* [Padel Friends Edge Service](https://padelfriendsapp.herokuapp.com/)
* [User Service](https://userserviceapipadel.herokuapp.com/)

<p align="center"><img width="700" height="300" src="https://i.ibb.co/P1khkKZ/Screenshot-6.png"></p>
<p align="center"><img width="800" height="200" src="https://i.ibb.co/1XPhnB2/Screenshot-7.png"></p>

## Documentation

### Swagger
The **Swagger documentation** is available for the microservices in your local computer, to access it you must enter the following link: https://padelfriendsapp.herokuapp.com/swagger-ui.html

You'll find the page bellow where you can make requests from all the application.

<p align="center"><img width="1000" height="600" src="https://i.ibb.co/Lx2wWyw/Screenshot-8.png"></p>

### Postman
The **Postman documentation** is available with the url of the **microservices deployed in cloud**. Do to the hibernate of the microservices once deployed in heroku, at first it might take a few request to actually wake the microservice up and make it work.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/e450077c05b1a65ec630)

## Test Coverage

| Microservice | Class % | Method % | Line % |
| ------ | --------------- | --------------- | --------------- |
| Club Service   | 100 % | 96 % | 78 % |
| Group Service   | 100 % | 97 % | 83 % |
| Relations Service   | 100 % | 84 % | 91 % |
| Reservations Service   | 100 % | 87 % | 71 % |
| Padel Friends Edge Service   | 100 % | 99 % | 85 % |
| User Service   | 83 % | 13 % | 10 % |

## Front-End

https://padelfriends-1e8e0.web.app/

The front-end is only dedicated to the **Admin** and **Player** Users, to have to access to all functionality you must use **Swagger** in your computer.

This application contains an **admin view** and a **player view** depending on each user logs in.

<p align="center"><img width="800" height="300" src="https://i.ibb.co/zVFZCRK/Screenshot-10.png"></p>
<p align="center"><img width="800" height="300" src="https://i.ibb.co/MncqHC8/Screenshot-12.png"></p>
<p align="center"><img width="800" height="500" src="https://i.ibb.co/VpC9TWj/Screenshot-13.png"></p>
<p align="center"><img width="800" height="600" src="https://i.ibb.co/HtdSq55/Screenshot-14.png"></p>
<p align="center"><img width="800" height="400" src="https://i.ibb.co/hRn3XHd/Screenshot-17.png"></p>
<p align="center"><img width="800" height="400" src="https://i.ibb.co/QcJfw42/Screenshot-15.png"></p>
<p align="center"><img width="800" height="400" src="https://i.ibb.co/R3qZ8Xs/Screenshot-16.png"></p>


### New Framework Implementation
As a new framework I wanted to add MapBox for the following features:
- Show the location of the Club.
- Find the club's address on the map and save its coordinates for later display.

I would like to show a little bit how the integration has been done.

* Component and Service Implementation.

<p align="center"><img width="300" height="400" src="https://i.ibb.co/VvdgfmZ/Screenshot-18.png"></p>

* In order to collect the latitude and longitude of the club in question and display it on the map, attributes have been passed on the map label itself:

<p align="center"><img width="1200" height="300" src="https://i.ibb.co/DV7nb5j/Screenshot-19.png"></p>
<p align="center"><img width="800" height="400" src="https://i.ibb.co/qYpfgFb/Screenshot-20.png"></p>
<p align="center"><img width="800" height="400" src="https://i.ibb.co/BPLXKr8/Screenshot-21.png"></p>

* The EventEmmitter system has been used to save the coordinates of the search performed:

<p align="center"><img width="800" height="400" src="https://i.ibb.co/X8CCJ0W/Screenshot-22.png"></p>
<p align="center"><img width="1400" height="400" src="https://i.ibb.co/vJ563tv/Screenshot-26.png"></p>
<p align="center"><img width="800" height="400" src="https://i.ibb.co/N9Fk0Z3/Screenshot-24.png"></p>
<p align="center"><img width="400" height="500" src="https://i.ibb.co/M8qrWhX/Screenshot-25.png"></p>


### Credentials

| Username | Password |
| ------ | --------------- |
| admin   | Admin1234! |

To enter the application as an official **user** you must register first and then **login** with your **username** and matching **password**.

### Additional comment

The unfortunate application is not developed with the initial idea at 100% due to time logistics issues, the points that have been left out are
- Notice of new reservations created to the users of a specific group.
- Complete revision of correct permissions in the whole application.
- Correct management of error messages.
- 100% testing

However I hope you like the idea, thank you very much!