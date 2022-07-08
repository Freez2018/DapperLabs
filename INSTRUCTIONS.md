# DapperLabs
Project made in Java 8. 
1. run PostgreSQLJDBC to create User table in PostqreSQL if needed (Database name DapperLabs). Credentials might be changed in UserService.
2. run DapperLabsUsersApplication to start web server. (change port in application.properties if needed)
3. run UserTransformerTest for unit testing

# API endpoints:
GET http://localhost:8090/api/users
headers: x-authentication-token 

PUT http://localhost:8090/api/users
headers: x-authentication-token 
payload:  
{            
            "firstname": "Katy2",
            "lastname": "Borta",
            "email": "Borta@gmail.com"        
 }

POST http://localhost:8090/api/signup
payload:
{            
        "firstname": "Bob",
        "lastname": "Tula",
        "email": "Tula@gmail.com",
        "password": "dfgdh44"
   }
    
POST http://localhost:8090/api/login
payload:
{   
        "email": "Jogn@gamil.com",
        "password": "dslfjf"
 }
   
