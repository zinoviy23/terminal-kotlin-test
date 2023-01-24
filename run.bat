@echo off

if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.

set CURRENT=%cd%

cd %DIRNAME%
call %DIRNAME%gradlew.bat :installDist > nul

cd %CURRENT%

if "%~1" == "-e" (GOTO CLEAN_VARS) else (GOTO RUN)

:CLEAN_VARS
echo "Some cleanup"
if not "%IDEA_INITIAL_DIRECTORY%" == "" set "MY_IDEA_TERMINAL=true"
if "%MY_IDEA_TERMINAL%" == "true" set "COLORTERM=truecolor"
set "IDEA_INITIAL_DIRECTORY="
set "__INTELLIJ_COMMAND_HISTFILE__="
set "TERMINAL_EMULATOR="
call chcp 65001 > nul

:RUN
set "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
call %DIRNAME%build\install\terminal-playground\bin\terminal-playground.bat %*

if "%OS%"=="Windows_NT" endlocal
