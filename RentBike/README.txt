Name of Application : Rent&Ride
Language Used: JAVA 8
Framework: Spring MVC/ Hibernate
DB Used: MySql Workbench 8.0
Server: Tomcat v9.0
UI: jsp/js/Ajax/jQuery/Bootstap

To run the project on a particlular machine, please follow below steps:
 1. You need to have jdk1.8, eclipse and tomcatv9.0 installed on your system.
 2. Unzip the source code. The project should be then imported as a maven project. We have a pom.xml file to            resolve jar dependencies. 
 3. Please update maven by right click on the project --> Maven --> Update Project, select the project and click OK
 4. Please maven clean + install the project (Right click on project --> Run As --> Maven Clean and then Maven         Install)
 5. Please install MySql Workbench 8.0 , We have pasted the dump file(dumpFile.sql) in source code. Please run this     sql file in your workbench so that DB setup is ready
 6. In eclipse, go to hibernate.cfg.xml and change your hibernate.connection.url, hibernate.connection.username,        hibernate.connection.password according to what you have given while confguring MySql Workbench.
 7. Import tomcatv9.0 as the server, add the project and run it.
 8. You can access the application on browser : http://localhost:8080/RentBike	