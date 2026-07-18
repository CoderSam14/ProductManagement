@echo off
echo Starting Product Management System...
echo.

echo Installing frontend dependencies...
call npm install

echo.
echo Starting both backend and frontend...
echo Backend will start on http://localhost:8080
echo Frontend will start on http://localhost:3000
echo.

start "Backend" cmd /k "mvn spring-boot:run"
timeout /t 10
start "Frontend" cmd /k "npm run dev"

echo.
echo Both servers are starting...
echo Check the opened windows for server status
pause
