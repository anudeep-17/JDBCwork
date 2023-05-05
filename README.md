# Homework 8 - postgres JDBC work 
## HOW TO RUN: <br/>
Steps 1: open the terminal and navigate to the given files. <br/>
Step 2 : compile the files in databasehw8 package using the command given below
```
javac -d bin -cp src src/databasehw8/*.java
```
Step 3: Now navigate to the bin folder
```
cd bin
```
Step 4: Now run the java files with the arguments
```
java -p "..\src\postgresql-42.6.0.jar" -cp . databasehw8.driver student instructor   
or 
java -p "<file/path/postegres.jar>" -cp . databasehw8.driver tablename1 tablename2   
```
Step5: view the output

## Example Output:

<img src="https://github.com/anudeep-17/JDBCwork/blob/main/output.png" />

## NOTE: 
have added testing using Junit testing in the pakage of testing while I couldnt figure out how to run it in terminal i am not providing instructions, if you can please do it as it has all combinations of estimate joins and runs the calculations.
