# Complete Setup Guide

## Prerequisites Installation

### 1. Install Node.js and npm
Download and install Node.js (which includes npm) from:
**https://nodejs.org/en/download/**

Choose the LTS version (recommended).

After installation, verify in a new terminal:
```bash
node --version
npm --version
```

### 2. Install Java Development Kit (JDK 21)
Download and install JDK 21 from:
**https://www.oracle.com/java/technologies/downloads/#jdk21-windows**

Or use OpenJDK:
**https://adoptium.net/temurin/releases/?version=21**

After installation, verify:
```bash
java --version
javac --version
```

### 3. Install Maven
Download Maven from:
**https://maven.apache.org/download.cgi**

Follow installation instructions for your OS.

Verify:
```bash
mvn --version
```

## Project Setup

### 1. Clone/Navigate to Project
```bash
cd "C:\#Self Study\Projects\ProductManagement"
```

### 2. Install Frontend Dependencies
```bash
npm install
```

### 3. Build Frontend
```bash
npm run build
```

### 4. Start Development Servers

**Option A: Both servers simultaneously**
```bash
npm run dev:full
```

**Option B: Start separately**

Terminal 1 (Backend):
```bash
mvn spring-boot:run
```

Terminal 2 (Frontend):
```bash
npm run dev
```

## Common Issues and Solutions

### Issue: "npm not recognized"
**Solution:** Install Node.js from nodejs.org and restart your terminal.

### Issue: "java not recognized" 
**Solution:** Install JDK 21 and add to PATH environment variable.

### Issue: "mvn not recognized"
**Solution:** Install Maven and add to PATH environment variable.

### Issue: CSS import errors
**Solution:** Make sure all dependencies are installed with `npm install` first.

### Issue: Port already in use
**Solution:** 
- Frontend: Change port with `npm run dev -- -p 3001`
- Backend: Add `server.port=8081` to application.properties

## Deployment

Once everything works locally:

1. **Push to GitHub** (already done)
2. **Deploy Frontend to Vercel**
3. **Deploy Backend to Railway/Heroku**

See `DEPLOYMENT.md` for detailed deployment instructions.

## Project URLs

- **GitHub Repository:** https://github.com/CoderSam14/ProductManagement
- **Frontend (Dev):** http://localhost:3000
- **Backend (Dev):** http://localhost:8080
- **API Docs:** http://localhost:8080/api/products
