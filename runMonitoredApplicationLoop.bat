@ECHO OFF
:loop
call runMonitoredApplication.bat
timeout 2
goto loop