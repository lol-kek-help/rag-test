@ECHO OFF
SETLOCAL

REM Minimal Maven wrapper fallback for this repository.
WHERE mvn >NUL 2>NUL
IF %ERRORLEVEL% EQU 0 (
  mvn %*
  EXIT /B %ERRORLEVEL%
)

ECHO Error: 'mvn' not found in PATH. Install Maven 3.9+ and run again.
EXIT /B 1
