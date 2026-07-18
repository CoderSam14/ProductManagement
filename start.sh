#!/bin/bash

echo "Starting Product Management System..."
echo

echo "Installing frontend dependencies..."
npm install

echo
echo "Starting both backend and frontend..."
echo "Backend will start on http://localhost:8080"
echo "Frontend will start on http://localhost:3000"
echo

# Start backend in background
echo "Starting backend..."
mvn spring-boot:run &
BACKEND_PID=$!

# Wait a bit for backend to start
sleep 10

# Start frontend
echo "Starting frontend..."
npm run dev &
FRONTEND_PID=$!

echo
echo "Both servers are running!"
echo "Backend PID: $BACKEND_PID"
echo "Frontend PID: $FRONTEND_PID"
echo
echo "Press Ctrl+C to stop both servers"

# Function to kill both processes on script exit
cleanup() {
    echo "Stopping servers..."
    kill $BACKEND_PID 2>/dev/null
    kill $FRONTEND_PID 2>/dev/null
    exit 0
}

trap cleanup INT

# Wait for user to stop
wait
