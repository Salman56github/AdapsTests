@echo off

echo ================================
echo Selenium job started
echo Date & Time: %DATE% %TIME%
echo ================================

cd /d D:\Perso_Docs\AdapsAssertTest

java -jar target\AdapsAssertTest-1.0-SNAPSHOT.jar

echo ================================
echo Selenium job finished
echo Date & Time: %DATE% %TIME%
echo ================================
