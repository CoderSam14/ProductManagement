# Product Management System

A modern, full-stack Product Management System built with Java Spring Boot backend and Next.js React frontend.

## Features

- **Product Management**: Create, view, and manage products (Food and Drinks)
- **Rating System**: 5-star rating system with visual star display
- **Review System**: Add and view product reviews
- **Localization**: Multi-language support (English, Russian, French, Chinese)
- **Modern UI**: Responsive design with Tailwind CSS
- **Real-time Updates**: Dynamic product and review updates

## Tech Stack

### Backend
- Java 21
- Spring Boot 3.2
- H2 In-Memory Database
- Maven

### Frontend
- Next.js 14
- React 18
- TypeScript
- Tailwind CSS
- Axios for API calls

## Project Structure

```
├── src/main/java/labs/pm/          # Java Backend
│   ├── controller/                 # REST Controllers
│   ├── data/                      # Data Models
│   ├── dto/                       # Data Transfer Objects
│   └── ProductManagementApplication.java
├── app/                           # Next.js App Router
├── components/                    # React Components
├── services/                      # API Services
├── types/                         # TypeScript Types
├── pom.xml                        # Maven Configuration
├── package.json                   # Node.js Dependencies
└── vercel.json                    # Vercel Deployment Config
```

## Getting Started

### Prerequisites
- Java 21+
- Node.js 18+
- Maven 3.6+

### Development Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ProductManagement
   ```

2. **Start the Backend**
   ```bash
   mvn spring-boot:run
   ```
   Backend will start on http://localhost:8080

3. **Install Frontend Dependencies**
   ```bash
   npm install
   ```

4. **Start the Frontend**
   ```bash
   npm run dev
   ```
   Frontend will start on http://localhost:3000

### API Endpoints

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product
- `POST /api/products/{id}/reviews` - Add review to product
- `GET /api/products/{id}/reviews` - Get product reviews
- `PUT /api/products/locale/{locale}` - Change locale
- `GET /api/products/locales` - Get supported locales

## Deployment

### Vercel Deployment

1. **Push to GitHub**
   ```bash
   git add .
   git commit -m "Initial commit"
   git push origin main
   ```

2. **Deploy to Vercel**
   - Connect your GitHub repository to Vercel
   - Vercel will automatically detect and deploy both frontend and backend
   - The `vercel.json` configuration handles the deployment setup

3. **Environment Variables**
   Set the following in your Vercel dashboard:
   - `JAVA_VERSION`: 21

### Manual Build

**Backend:**
```bash
mvn clean package
java -jar target/product-management-1.0.0.jar
```

**Frontend:**
```bash
npm run build
npm start
```

## Features Overview

### Product Types
- **Food**: Has expiry dates and time-based discounts
- **Drink**: Has happy hour discounts (5:30-6:30 PM)

### Rating System
- NOT_RATED: ☆☆☆☆☆
- ONE_STAR: ★☆☆☆☆
- TWO_STAR: ★★☆☆☆
- THREE_STAR: ★★★☆☆
- FOUR_STAR: ★★★★☆
- FIVE_STAR: ★★★★★

### Supported Locales
- en-GB (English - UK)
- en-US (English - US)
- ru-RU (Russian)
- fr-FR (French)
- zh-CN (Chinese)

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

Copyright © 2025 Sameer. All rights reserved.

---

Built with ❤️ using Java Spring Boot and Next.js
