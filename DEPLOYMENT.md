# Complete Deployment Guide

## 🚀 Quick Start

### Option 1: Windows (start.bat)
```cmd
start.bat
```

### Option 2: Unix/Linux/Mac (start.sh)
```bash
chmod +x start.sh
./start.sh
```

### Option 3: Manual Start
```bash
# Terminal 1 - Backend
mvn spring-boot:run

# Terminal 2 - Frontend  
npm install
npm run dev
```

## 🌐 Production Deployment

### Frontend (Vercel)
1. Push to GitHub
2. Connect repository to Vercel
3. Set environment variable: `NEXT_PUBLIC_API_URL=https://your-backend-url`
4. Deploy automatically

### Backend (Railway - Recommended)
1. Sign up at https://railway.app
2. Connect GitHub repository
3. Railway auto-detects Spring Boot
4. Set environment variables if needed
5. Get deployment URL

### Alternative Backend (Heroku)
```bash
heroku create your-app-name
heroku buildpacks:set heroku/java
git push heroku main
```

## 📋 Project Features

✅ **Backend (Spring Boot)**
- REST API endpoints
- H2 in-memory database
- Product management (Food/Drink)
- Review system
- Multi-language support
- CORS configured

✅ **Frontend (Next.js + React)**
- Modern responsive UI
- Product listing and details
- Add/view reviews
- Create new products
- Language switching
- Real-time updates

✅ **Deployment Ready**
- Vercel configuration
- Static export for CDN
- Environment variables
- Build scripts
- Git ignore files

## 🔗 API Endpoints

- `GET /api/products` - List all products
- `GET /api/products/{id}` - Get product details
- `POST /api/products` - Create product
- `POST /api/products/{id}/reviews` - Add review
- `GET /api/products/{id}/reviews` - Get reviews
- `PUT /api/products/locale/{locale}` - Change language

## 🌍 Supported Languages

- English (UK/US)
- Russian
- French 
- Chinese

## 📁 Project Structure

```
ProductManagement/
├── src/main/java/labs/pm/     # Java Backend
│   ├── controller/            # REST APIs
│   ├── data/                  # Models
│   └── dto/                   # Data Transfer Objects
├── app/                       # Next.js App
├── components/                # React Components
├── services/                  # API Services
├── types/                     # TypeScript Types
├── pom.xml                    # Maven Config
├── package.json               # Node Dependencies
├── vercel.json                # Vercel Config
└── README.md                  # Documentation
```

## 🎯 Access Points

- **Development Frontend**: http://localhost:3000
- **Development Backend**: http://localhost:8080
- **API Documentation**: http://localhost:8080/api/products
- **H2 Console**: http://localhost:8080/h2-console

## 🛠️ Technologies

**Backend:**
- Java 21
- Spring Boot 3.2
- Maven
- H2 Database

**Frontend:**
- Next.js 14
- React 18
- TypeScript
- Tailwind CSS
- Heroicons

**Deployment:**
- Vercel (Frontend)
- Railway/Heroku (Backend)

---

Your Product Management System is now complete and deployment-ready! 🎉
