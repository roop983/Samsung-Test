# Samsung-Test

Scenario : A person walking on road. Stops at Signal 1. And then walks again and stops at Signal 2. Get Real Time coordinates and compare to the expected values

Approach Taken:
1. Get real time coordinates using Api key. Only could be taken at Signal 1 as movement is needed till Signal 2.
2. Tried with a different flow by searching for 2 locations in Google Maps. Then extracted the coordinates and asserted with the expected values

The project has been devloped using Java, Selenium Webdriver, Maven and TestNg. I have developed an end to end solution using page objects model design pattern. Also I have made use of reusuable methods and maintained separate config, testdata and util file. Tests can be accessed at src/test/java/com/samsung/qa/testcases directory

Please follow the below steps to execute the code.

1. Clone project from github location https://github.com/roop983/Samsung-Test
2. Upload the project in an IDE and run the testng.xml file (/SamsungTest/testng.xml) or else run as Maven test
3. To run in the terminal, navigate to the local project path and execute the below maven commands:
mvn clean
mvn install
mvn test
NOTE: Ensure Chrome browser is present, as this project is designed to run only in this browser.