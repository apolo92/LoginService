# LoginService

#Introduction

This application Java is a server HTTPServer , that offers login service API-REST to create, modify and read the differents users. Only Admin have access to modify and create users. The users only can access to pages assign Admin. 

# Deploy

1. Clone git in local machine
2. Run maven install in folder target is a executable jar
3. In console execute java -jar user-1.0-SNAPSHOT-jar-with-dependencies
4. Open web browser: localhost:8000/login
5. Users , password , role load default:
   - user1 : user1 : ROLE1 -> access page1
   - user2 : user2 : ROLE2 -> access page2
   - user3 : user3 : ROLE3 -> access page3
   - Admin : Admin : ADMIN -> access in all pages
   
#API-REST

-The url acces is localhost:8000/users?(token access login page)

-JSON POST service :

   {
       "username":"alex",
       "password":"1234",
       "role":[ROLE1]
   }
   
-JSON PUT service :

   {
       "username":"alex",
       "password":"1234",
       "role":[ROLE1,ROLE2],
       "replaceUserName":"alex2"
   }
