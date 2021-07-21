# Java MVC-D

### College Project written in Java.

Theme:
- Simple institute data management (teachers, students, courses, subjects)

Features: 
- MVC Pattern 
- Data Layer (MySQL) access.
- Debugged UI navigation with focus in business logic protection and data integrity
- Simple error handling


<a href="https://github.com/p-miralles/my-images/blob/main/ProyFinalProgII.PNG?raw=true"> <img width="1200px" alt="completeMenu" src="https://github.com/p-miralles/my-images/blob/main/ProyFinalProgII.PNG?raw=true" /></a> 


## Running the project
- Create a new empty project
- Include library:  mysql-connector-java-5.1.16-bin (you can find it easily on google)
- Create a MySQL database server and restore the provided database (prog2.sql) with the following setup and credentials: 
  - Address: localhost
  - user: root
  - pass: mysql
  - port: 3306
- Paste all folders in your java project src directory
- RUN AND ENJOY!!


### Simple Database importing tips (if you don´t know how to make that)
- Install db engine: AMPPS
- Turn on Mysql button
- Install gestor connector: SQLyog
- Create a new connection with the given credentials (localhost,root,mysql,3306)
- Database: prog2
- Open prog2 file and:
  - Replace the first line with: CREATE DATABASE  IF NOT EXISTS `prog2`;
  - On the first pharagraph below this line:
  /*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
  add:  
  SET FOREIGN_KEY_CHECKS=0;
  - Before the line
  Dump completed on 2020-06-20 23:17:23
  - Add 
  SET FOREIGN_KEY_CHECKS=1;
  - On SQLyog, give right click on root@localhost, execute SQL script, and find the current file
  - You have now the database backup loaded


### Support my insomnia nights😁
✔ https://www.buymeacoffee.com/pmiralles
<p>✔ https://cafecito.app/pmiralles</p>
