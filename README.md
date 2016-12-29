# Project Repository for Course CS 4701 at Cornell
## AI solver for the game 2048. 

Here are the step-by-step instructions for setting up the server locally:
* Download Apache Ant: https://tomcat.apache.org/download-80.cgi ,and note down the path to "ant" executable.
* Go to the project directory, execute: ```<path-to-ant>/ant -f 2048gamesolver.xml```.  
  E.g. ```~/Downloads/apache-ant-1.9.7/bin/ant -f 2048gamesolver.xml```
* Now Download Tomcat 8.0(Core): https://tomcat.apache.org/download-80.cgi. Note down the path to your tomcat executables
* From your project directory, execute: ```cp -R out/artifacts/WebApp_Exploded/* <path-to-your-tomcat-folder>/webapps/ROOT/``` 
  E.g. cp -R out/artifacts/WebApp_Exploded/* ~/Downloads/apache-tomcat-8.5.9/webapps/ROOT/
* Change the permission for your catalina executable: ```sudo chmod 777 <path-to-your-apache-tomcat-folder>/bin/catalina.sh```
  E.g. sudo chmod 777 /Users/z/Downloads/apache-tomcat-8.5.9/bin/catalina.sh
* Now execute ```<path to tomcat>/bin/catalina.sh run```
  E.g. /Users/z/Downloads/apache-tomcat-8.5.9/bin/catalina.sh run
* Open your browser and go to: http://localhost:8080/home
