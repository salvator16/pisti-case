#Pisti Game with Paralel Processing
Command Line based async pisti game with Bots. Two type of bot exist, smart and dummy.

# Installing & Running from source

A step by step series how to install the game
```
cd pisti-case
mvn install -DskipTests
cd target/classes
java com.exam.pisti


# Installing & Running from jar
cd target
java -jar pisti-0.0.1-SNAPSHOT.jar

```
# Running the tests
```
mvn test
```

# Built With


- Java 8 - Primary Language
- Maven - Dependency Management
- JUnit 1.4, Unit Tests

# Authors

Ahmet Asa- ahmetasa16@gmail.com
# Expected Inputs
Player Count    
Each game repeatCount   
Dummy Bot name  
Smart Bot Name

# Expected Outputs
--------Enter Player Count-------- :    
12  
--------Enter Game repeat Count-------- :   
2   
--------Enter Dummy Bot Name -------- :     
DummyBot    
--------Enter Smart Bot Name -------- :     
SmartBot
    
------LEADERBOARD------        

DummyBot-0=39   
DummyBot-10=35  
DummyBot-6=32   
SmartBot-7=28   
DummyBot-2=27   
DummyBot-4=22   
SmartBot-11=18  
SmartBot-5=14   
DummyBot-8=8    
SmartBot-1=6    
SmartBot-9=5    
SmartBot-3=4    
