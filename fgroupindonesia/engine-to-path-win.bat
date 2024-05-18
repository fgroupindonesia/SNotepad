@echo off

echo Installing Mini JRE Engine from FgroupIndonesia... 

if not defined JAVA_HOME (

	echo didnt have JAVA Location here!
	set myPath=%APPDATA%\\fgroupindonesia\\jdk-11-mini-jre
	setx /M JAVA_HOME %myPath%

	echo "%PATH%" | find /i "%myPath%" > nul
	if %errorlevel% equ 0 (
		echo The directory is already listed in the PATH variable.
	) else (
		echo The directory is not listed in the PATH variable.
		setx /M PATH "%PATH%;%myPath%\bin"
	)	

) else (
	echo JAVA Location is already defined! Shown at %JAVA_HOME%
)

REM echo %PATH%
echo Process is Finished!

