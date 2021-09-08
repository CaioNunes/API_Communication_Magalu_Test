# LuizaLabs - API Communication

1. To run the application, just have the docker installed and run the following command: 
 `docker-compose up`
 
2. If you want to execute locally, you need JDK 11, PostgreSQL and Maven installed. Then, run the following commands in project folder: 
 `mvn clean install` 

 `java -jar -Dspring_datasource_username=postgres -Dspring_datasource_password=root target/communication-api-0.0.1-SNAPSHOT.jar` 

3. To view the Swagger, after run the application, access the url: http://localhost:8080/swagger-ui.html 
 
4. mvn automatically runs a set of tests. To see the Jacoco's report, access project_folder/target/site/jacoco/index.html .
