@echo off
cd /d "%~dp0"
echo Compiling Coding Platform with MySQL Connector...
javac -cp "lib/*" -d bin src\com\codingplatform\*.java
if %ERRORLEVEL% EQU 0 (
    echo Starting Application...
    start javaw -cp "bin;lib/*" com.codingplatform.Main
) else (
    echo Compilation failed.
    pause
)
