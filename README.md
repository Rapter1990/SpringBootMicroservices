# Spring Boot Microservice Example(Eureka Server, Config Server, API Gateway, Services , RabbitMq, Keycloak)

<img src="screenshots/springbootmicroservices.drawio_image.png" alt="Main Information" width="800" height="900">

# About the project
<ul style="list-style-type:disc">
  <li>User can register and login through Keycloak</li>
  <li>User can register and login through Keycloak</li>
  <li>Admin can create, update, delete advertisement and get advertisement by its is and get all advertisements from management service to advertisement service through API Gateway</li>
  <li>Admin can approve and reject advertisement from advertisement service to report service by using managment service through API Gateway</li>
  <li>User canget advertisement by its is and get all advertisements from management service to advertisement service through API Gateway</li>
  <li>The view count of the approved advertisement are increasing when user try to show it</li>
</ul>

7 services whose name are shown below have been devised within the scope of this project.

- Config Server
- Eureka Server
- API Gateway
- User Service
- Management Service
- Advertisement Service
- Report Service

### 🔨 Run the App

<b>Docker</b>

<b>1 )</b> Install <b>Docker Desktop</b>. Here is the installation <b>link</b> : https://docs.docker.com/docker-for-windows/install/

<b>2 )</b> Open <b>Terminal</b> under <b>resources</b> folder to run <b>Keycloak</b> and <b>RabbitMq</b> on <b>Docker</b> Container
```
    docker-compose up -d
```
<b>3 )</b> Implement Keycloak Settings
```
    1 ) Open Keycloak on the Browser through localhost:8181
    2 ) Enter username and password (admin : admin)
    3 ) Create Client named for spring-boot-microservice-keycloak and define it in Keycloak config of user service
    4 ) Change client's access type from public to confidential
    5 ) Get secret key to define clientSecret in Keycloak config of user service
    6 ) Define roles for Admin and User as ROLE_ADMIN and ROLE_USER
```

<b>4 )</b> Implement Rabbitmq Settings
```
    1 ) Open Rabbitmq on the Browser through http://localhost:15672
    2 ) Enter username and password (rabbitmq : 123456)
    3 ) Open Admin section in the navbar
    4 ) Define new user named guest and its username , password (guest : guest , role : administrator) , next give all permissiion (Virtual host : "/" , regexp : ".*")
```

<b>Maven></b>

<b>1 )</b> Start Keycloak and Rabbit through Docker

<b>2 )</b> Implement their settings

<b>3 )</b> Download your project from this link `https://github.com/Rapter1990/SpringBootMicroservices`

<b>4 )</b> Go to the project's home directory :  `cd SpringBootMicroservices`

<b>5 )</b> Create a jar file though this command `mvn clean install`

<b>6 )</b> Run the project though this command `mvn spring-boot:run`


### To execute the API's through the gateway
    1) http://localhost:8600/api/v1/users/signup
    2) http://localhost:8600/api/v1/users/login
    3) http://localhost:8600/api/v1/users/info 
    4) http://localhost:8600/api/v1/management/admin_role/create/{user_id} 
    5) http://localhost:8600/api/v1/management/admin_role/alladvertisements
    6) http://localhost:8600/api/v1/management/admin_role/alladvertisements/{advertisement_id} 
    7) http://localhost:8600/api/v1/management/admin_role/update/{advertisement_id}
    8) http://localhost:8600/api/v1/management/admin_role/delete/{advertisement_id} 
    9) http://localhost:8600/api/v1/management/admin_role/advertisement/{advertisement_id}/approve
    10) http://localhost:8600/api/v1/management/admin_role/advertisement/{advertisement_id}/reject
    11) http://localhost:8600/api/v1/management/user_role/alladvertisements
    12) http://localhost:8600/api/v1/management/user_role/advertisement/{advertisement_id} 
    

Explore Rest APIs
<table style="width:100%">
  <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Description</th>
      <th>Valid Request Body</th>
      <th>Valid Request Params</th>
      <th>Valid Request Params and Body</th>
      <th>No Request or Params</th>
  </tr>
  <tr>
      <td>POST</td>
      <td>signup</td>
      <td>Sign Up for User and Admin</td>
      <td><a href="README.md#signup">Info</a></td>
      <td></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>POST</td>
      <td>login</td>
      <td>Login</td>
      <td><a href="README.md#login">Info</a></td>
      <td></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>info</td>
      <td>Get User's Role Information (ROLE_USER or ROLE_ADMIN)</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#info">Info</a></td>
  </tr>
  <tr>
     <td>POST</td>
     <td>create/{user_id}</td>
     <td>Create Advertisement for User</td>
     <td></td>
     <td></td>
     <td><a href="README.md#create">Info</a></td>
     <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>alladvertisements</td>
      <td>Get all advertisements From Admin</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#alladvertisementsFromAdmin">Info</a></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>alladvertisements/{advertisement_id}</td>
      <td>Get advertisement by Id From Admin</td>
      <td></td>
      <td><a href="README.md#advertisementById">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>PUT</td>
      <td>update/{advertisement_id}</td>
      <td>Update advertisement by Id</td>
      <td></td>
      <td></td>
      <td><a href="README.md#update">Info</a></td>
      <td></td>
  </tr>
  <tr>
      <td>DELETE</td>
      <td>delete/{advertisement_id} </td>
      <td>Delete advertisement by Id</td>
      <td></td>
      <td><a href="README.md#delete">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>advertisement/{advertisement_id}/approve</td>
      <td>Approve advertisement By Id</td>
      <td></td>
      <td><a href="README.md#approve">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>advertisement/{advertisement_id}/reject</td>
      <td>Reject advertisement By Id</td>
      <td></td>
      <td><a href="README.md#reject">Info</a></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>alladvertisements</td>
      <td>Get all advertisements From User</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#alladvertisementsFromUser">Info</a></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>alladvertisements</td>
      <td>alladvertisements/{advertisement_id}</td>
      <td></td>
      <td></td>
      <td></td>
      <td><a href="README.md#advertisementByIdFromUser">Info</a></td>
  </tr>
</table>


### Used Dependencies
* Core
  * Spring
    * Spring Boot
    * Spring Security
    * Spring Web
      * RestTemplate
    * Spring Data
      * Spring Data JPA
    * Spring Cloud
      * Spring Cloud Gateway Server
      * Spring Cloud Config Server
      * Spring Cloud Config Client
  * Netflix
    * Eureka Server
    * Eureka Client
* Database
  * Mysql
* Message Broker
  * RabbitMQ
* Security
  * Keycloak Server
  * Keycloak OAuth2
  * Keycloak REST API

## Valid Request Body

##### <a id="signup">Sign Up for User and Admin
```
    http://localhost:8600/api/v1/users/signup

    {
        "username" : "springbootmicroserviceuser",
        "password" : "user123456",
        "name" : "Micro User",
        "surname" : "User Surname",
        "phoneNumber" : "123456789",
        "email" : "springbootmicroserviceuser@user.com",
        "role" : "ROLE_USER"
    }

    http://localhost:8600/api/v1/users/signup

    {
        "username" : "springbootmicroserviceadmin",
        "password" : "admin123456",
        "name" : "Micro Admin",
        "surname" : "Admin Surname",
        "phoneNumber" : "123456789",
        "email" : "springbootmicroserviceadmin@admin.com",
        "role" : "ROLE_ADMIN"
    }
```

##### <a id="login">Login
```
    http://localhost:8600/api/v1/users/login
    Bearer Token : Access Token of User from Keycloak
    {
        "username" : "springbootmicroserviceuser",
        "password" : "user123456"
    }

    http://localhost:8600/api/v1/users/login
    Bearer Token : Access Token of Admin from Keycloak
    {
        "username" : "springbootmicroserviceadmin",
        "password" : "admin123456"
    }
```

## Valid Request Params

##### <a id="advertisementById">Get advertisement by Id From Admin
```
    http://localhost:8600/api/v1/management/admin_role/alladvertisements/{advertisement_id} 
    Bearer Token : Access Token of Admin from Keycloak
```

##### <a id="delete">Delete advertisement by Id 
```
    http://localhost:8600/api/v1/management/admin_role/delete/{advertisement_id} 
    Bearer Token : Access Token of Admin from Keycloak
```

##### <a id="approve">Approve advertisement By Id
```
    http://localhost:8600/api/v1/management/admin_role/advertisement/{advertisement_id}/approve
    Bearer Token : Access Token of Admin from Keycloak
```

##### <a id="reject">Reject advertisement By Id
```
    http://localhost:8600/api/v1/management/admin_role/advertisement/{advertisement_id}/reject
    Bearer Token : Access Token of Admin from Keycloak
```

##### <a id="advertisementByIdFromUser">Get advertisement by Id From User
```
    http://localhost:8600/api/v1/management/user_role/alladvertisements/{advertisement_id} 
    Bearer Token : Access Token of Admin from Keycloak
```

## Valid Request Params and Body

##### <a id="create">Create Advertisement for User
```
    http://localhost:8600/api/v1/management/admin_role/create/{user_id} 
    Bearer Token : Access Token from Keycloak
    {
        "title" : "Advertisement 1 for User 1",
        "price" : 200
    }
```

##### <a id="update">Create Route by City Id and Destination City Id
```
    http://localhost:8600/api/v1/management/admin_role/update/{advertisement_id}
    Bearer Token : Access Token from Keycloak 
    {
        "title" : "Advertisement 1 for User 1 Updated",
        "price" : 300
    }
```

## No Request or Params

##### <a id="info"> Get User's Role Information (ROLE_USER or ROLE_ADMIN)
```
    http://localhost:8600/api/v1/users/info
    Bearer Token : Access Token of Admin or User from Keycloak 
```

##### <a id="alladvertisementsFromAdmin"> Get all advertisements From Admin
```
    http://localhost:8600/api/v1/management/admin_role/alladvertisements
    Bearer Token : Access Token of Admin from Keycloak 
```

##### <a id="advertisementByIdFromUser"> Get all advertisements From User
```
    http://localhost:8600/api/v1/management/user_role/alladvertisements
    Bearer Token : Access Token of User from Keycloak 
```

### Screenshots

<details>
<summary>Click here to show the screenshots of project</summary>
    <p> Figure 1 </p>
    <img src ="screenshots/keycloak_1.PNG">
    <p> Figure 2 </p>
    <img src ="screenshots/keycloak_2.PNG">
    <p> Figure 3 </p>
    <img src ="screenshots/keycloak_3.PNG">
    <p> Figure 4 </p>
    <img src ="screenshots/keycloak_4.PNG">
    <p> Figure 5 </p>
    <img src ="screenshots/keycloak_5.PNG">
    <p> Figure 6 </p>
    <img src ="screenshots/keycloak_6.PNG">
    <p> Figure 7 </p>
    <img src ="screenshots/keycloak_7.PNG">
    <p> Figure 8 </p>
    <img src ="screenshots/rabbitmq_1.PNG">
    <p> Figure 9 </p>
    <img src ="screenshots/rabbitmq_2.PNG">
    <p> Figure 10 </p>
    <img src ="screenshots/rabbitmq_3.PNG">
    <p> Figure 11 </p>
    <img src ="screenshots/rabbitmq_4.PNG">
</details>   