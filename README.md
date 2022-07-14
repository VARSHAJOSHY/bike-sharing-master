# bike-sharing-master

Software system to support a "bike-share program" in Glasgow. 

	o Name of Application : Rent&Ride
	o Language Used: JAVA 8
	o Framework: Spring MVC/ Hibernate
	o DB Used: MySql Workbench 8.0
	o Server: Tomcat v9.0
	o UI: jsp/js/Ajax/jQuery/Bootstap
	
The application should be accessible by three different roles - Customers, Operators, and Managers.
	
	o Customers - End-user of the application who rent and return the bikes.
	o Operators - People who assess, maintain, and manage the system.
	o Managers - People who generate and view the various type of reports.

User Registration:	Users can fill in their personal information through the registration system, which allows them to create an account to use the application.

Users will be authenticated through a login system with credentials (email id & password) before using the application.

**Functionalities**: 

	o Rent a Bike : Renting a bike is the fundamental function of any bike-sharing system; A simple bike rental process can give a 
	better user experience, thereby bringing Customer happiness.
	o Return a Bike : Users should be able to return the bikes upon finishing their ride no matter where they are.
	o Review a Bike : Customers' valuable insights concerning the ride and the application can make product improvements.
	o Report Defect : The app will be captured the information regarding defective bikes by providing a frontend feature to Customers 
	for reporting the issues. This ensures better tracking of defective bikes across the stations and thereby quick resolution.
	o Payment : Users should be allowed to pay for their current ride after the ride finishes.
	o Track Bike : This feature allows Operators to track the location of bikes all over the city which increases their productivity.
	o Move Bike : Operators can move the bikes between stations across cities according to the Customer's demands.
	o Repair Bike : Operators can fix the defective bikes reported by the Customers and update the bike status in the system so that 
	bikes will be available for the next rides.
	o Report Generation : Bike activities over a defined period can display in various forms such as tabular, graphs, etc. which helps 
	Manager to monitor the	entire system performance.

To run the project on a particlular machine, please follow below steps:

 1. You need to have jdk1.8, eclipse and tomcatv9.0 installed on your system.
 2. Unzip the source code. The project should be then imported as a maven project. We have a pom.xml file to            resolve jar dependencies. 
 3. Please update maven by right click on the project --> Maven --> Update Project, select the project and click OK
 4. Please maven clean + install the project (Right click on project --> Run As --> Maven Clean and then Maven         Install)
 5. Please install MySql Workbench 8.0 , We have pasted the dump file(dumpFile.sql) in source code. Please run this     sql file in your workbench so that DB setup is ready
 6. In eclipse, go to hibernate.cfg.xml and change your hibernate.connection.url, hibernate.connection.username,        hibernate.connection.password according to what you have given while confguring MySql Workbench.
 7. Import tomcatv9.0 as the server, add the project and run it.
 8. You can access the application on browser : http://localhost:8080/RentBike	
