# UIAndAPIAutomation
Steps to Execute
Step 1: Install Cucumber Eclipse Plugin:

Goto Eclipse -> Help -> Eclipse Marketplace -> Search for 'Cucumber Eclipse Plugin' -> Click Install
Step 2: Download the project from the GIT Repo

Step 3: Import the Maven project into Eclipse

Menu -> File -> Open Projects from File System -> Choose the import Source and Click Finish
Step 4: Expand the Project on Package Explorer

Step 5: Expand src/test/java -> runner file -> open the TestRunner.java

Step 6: To run the testcases, Right Click on TestRunner.java file -> Run As -> JUnit Test

Step 7: Verfiy the Status on the console and Verfiy the Logs in the log file (logs/mylog.log)

Step 8: Refresh the Project Folder and verify the /target/cucumber.json report file

Step 9: To generate the report, Right Click on the Project -> Run As -> Maven verify

Step 10: Validate all the generated Reports under target/cucumber-html-reports/
