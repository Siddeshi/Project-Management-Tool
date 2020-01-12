# Project-Management-Tool
Tool is to manage the projects that are being developed in an organisation

The tool is developed as a standalone spring boot application

####Required to softwares to run this app
    1. Maven 3.3.9
    2. MongoDB shell version v3.6.4 
    
####Running guidelines
- make sure maven home is set 
- start your mongodb server and create new DB `project_management_tool` and create a new user

       > use project_management_tool
       
       > db.createUser(
            {
              user: "sid",
              pwd: "pmt@sid",
              roles: [ "dbOwner" ]
            }
- Build the project with command `mvn clean install`
- Once build is successful run generated jar

        java -jar target/projectmanagement-tool-1.0-SNAPSHOT.jar

- Now you can check REST endpoints available to use through `swagger-ui` in the following url
  >http://localhost:8102/project-management-tool/swagger-ui.html

The default user id `sid` and password is `pmt@sid`

###### Thanks for visiting you can contact me through [linked in]
[linked in]: https://www.linkedin.com/in/siddeshbs/