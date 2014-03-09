SimpleApp is a simple application to show various conventions for using JEE technologies and patterns.

CONFIGURATION:  The application has been tested and run on Tomcat 7.  So you'll need that or something similar.  The database
is setup as a MySQL database using InnoDB:
  - You'll need a schema called "simpleapp" 
  - A user called "simpleapp", password: "simple314"
  - You'll need to run the database DDL script using the file "src/main/resources/database/createstuff.sql"
  - JDBC Connections are assumed to use "localhost:3306" for mysql database.  If you want something else, you'll need to change,
  	applicationContext.xml, hibernate.cfg.xml, hibernateAnnotations.cfg.xml, and src/main/webapp/MET-INF/context.xml
  	- The reason there are so many is that I'm not using a JNDI pool, and I'm individually testing JDBC, Hibernate using xml configuration,
  	  hibernate using Annotations, and Hibernate/Spring. 

Building:
  - Download the project using git clone. 
  - Maven options that you need are "mvn test", will run all tests, and "mvn tomcat:run" or "mvn tomcat7:run" for tomcat 7x to run tomcat for playing with webapp, rest stuff. Run these commands from the SimpleJEE/SimpleUser directory.
  - ANT build broken at the moment. I didn't update it after setting up maven.  You'll need to setup mysql, and use the source command to get the right stuff in the DB.

### Simple JDBC
First example is a simple JDBC application that stores, changes, retrieves data in a single table SIMPLEAPP.USER.
All actions are based on the base objects in the "src/main/java/com/wickedhobo/object" package.  There are two primary classes, 
User and Role following conventional Javabean standards with the addition of an constructors added simply 
for me to mess around.
  - I use simple DAO pattern for this found in classes/DAO.simpleDAO: "UserDAO"(interface) and "UserDAOImplSimple"(implementation).  
  - There's also a simple Factory pattern show in "classes/commonDAO"
  - Connections to the database are managed through the "DBConnection" class in the "simpleJDBC" package.
  - The JDBC Stuff I originally wrote cactus tests for, but switched to Maven/Spring tests, so there are no tests. Mostly just doing some straight JDBC stuff to remember how things work there. :)
	  
### Getting a little fancier: Hibernate, DAO, Generics 
The second example is sort of two examples combined into one.  The fist one is standard Hibernate using the
standard hibernate.cfg.xml style of development with mappings included in the related files.  The default is NOT
to use this, since I like annotations better.  So to use it with the tests, go to com.wickedhobo.DAO.hibernateDAO.HibUtil
and change the NOCONTEXTCONFIG variable to hibernate.cfg.xml and the tests will use the xml mappings.  Otherwise run
the UserHibernateTest and it'll use the annotations on the User.java and Role.java classes.  For any web examples I'm using
the annotations version and that is loaded in web.xml.

The methods tested are
- public void saveUser(User user) {...}
- public void updateUser(User user) {...}
- public User findUserByUsername(String username) {...}
- public void deleteUser(User user) {...}
- public List<User> listUsers() {...}
- public List<User> listUsersByRoles(String role) {...} (This one is to show successful implementation of the 
Many-to-Many relationship and the use of HQL and the HQL Entity facility and adding functionality subclasses).

You can see the test file at src/test/java/com/wickedhobo/UserHibernateTest

You can understand the database structure by looking at the createstuff.sql file, the hibernate config files, or the 
object.User and object.Role files by looking at the JPA annotations.

This sample also shows a much more advanced DAO strategy than that of the SimpleJDBC stuff using a stronger 
inheritance and calling model including generic type persistence objects which can be seen in 
DAO.hibernateDAO.CommonDAO and DAO.hibernateDAO.CommonDAOImpl.  The DAO.hibernateDAO.UserDAOImpl shows the 
actual implementation, the transactions, and the HQL if any.  Finally a UserDAOHibOnlyImpl for instantiation pattern for
this example.

I'm also using DAO.hibernateDAO.HibUtil to manage sessions, transactions, that sort of thing.

I know some people like to use a "Manager" style DAO as another level of indirection, but I figured I'd gone far 
enough for my purposes.

### Adding JPA/Annotations
The third and default example show's use of JPA annotations for the implementation, but in all other ways is exactly the same
as the one above.   I know it's not a true JPA application since I'm using Hibernate's session stuff rather than EntityManager convention.  So it's not a 
truly portable JPA application. 

Run the same test in sample two but using the default code in HibUtil.java. NOTE:the reason for Hibutil.java is to actually load the correct Hibernate configuration file at startup time inside a web container. 
 That's why it's a little painful for testing purposes.  Sorry.

### Adding Spring.  
The Spring sample is built using the sample DAO structure and inheritance structure as the Hibernate ones.  It's just using Spring
injection to manage the session, transactions, all that sort of thing.  You can see the samples in CommonDAOSpringImpl, UserDAOHibSpringImpl which
use the CommonDAO and UserDAO just as the last two examples did.

The test file is src/test/java/com/wickedhobo/UserSpringHibernateTest/java/

### MVC Sample and Simple Web Sample

I also wrote a simple Spring MVC sample.  You can see the controller at src/main/java/com/web/UserController and the Spring Test
at /src/test/java/com/wickedhobo/UserControllerTest.java. It has a fairly comprehensive set of Spring Tests for the
REST methods in the controller using Hamcrest for Matchers.

Also, if you deploy or run maven tomcat7:run you can go to localhost:8080/SimpleApp and see a list of links for a VERY simple
webapp that uses the MVC and maps to /result (src/main/resources/webapp/WEB-INF/JSP/result.jsp) which has some simple JEE EL
that gets data from the Model and displays.

### Rest Sample: 

To run the tests, run "mvn test" in the SimpleeJEE/SimpleUser directory

Lastly, there is a rest example that show's two different ways of sending JSON back to the caller.  One using the
Spring MVC Model and View type method and one that uses Jackson to serialize objects and pushes the JSON back using the @ResponseBody.

The REST Controller is at src/main/java/com/wickedhobo/rest/UserController.java.  Most methods use the @ResponseBody, but 
findByUserNameWithAction shows how you can also use a Spring Model And View pattern to return JSON/REST.

The test is at src/test/java/com/wickedhobo/UserControllerRestTest.java and has a fairly comprehensive set of Spring Tests for the
REST methods in the controller using Hamcrest for Matchers.

### Webapp:  

To Run the webapp, run "mvn tomcat:run" or "mvn tomcat7:run" (for tomcat 7x) from the SimpleJEE/SimpleUser directory.  You then have some cool things todo:

  - Simple webapp is here: http://localhost:8080/SimpleApp/    
  - It has no real error checking, but does the basics.

  - You can also make calls to the various REST interfaces using http or curl.  You're on your own for curl.  But some working calls:
     - http://localhost:8080/SimpleApp/userService/listUsersByRole/roleName/administrator.json
     - http://localhost:8080/SimpleApp/userService/listUsers.json
     - http://localhost:8080/SimpleApp/userService/findUserByUsernameWithAction/userName/mckerrj.json
     - http://localhost:8080/SimpleApp/userService/findUserByUsername/userName/mckerrj.json

You will also see all of the REST requests and results printed out on the command line when running "mvn test"

### OTHER
  - I use Logback for logging.  right now it's a very simple setup to send everything to the console.
  - It's been tested on OSX using java 6, and linux using java 7.
  - ** I've got the java level set in pom.xml to 1.7, so if on something else either remove or change.
  - Ant build file currently not working since I didn't update it after switching to Maven.  
  
