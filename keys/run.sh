#!/bin/sh
java -jar DailyMutualFundNAV-0.0.1-SNAPSHOT.jar
java -jar DailySharePrice-0.0.1-SNAPSHOT.jar
java -jar CalculateNetWorth-0.0.1-SNAPSHOT.jar
http-server dist/user-authentication