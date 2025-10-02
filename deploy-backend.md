# Backend Deployment Guide

## Railway Deployment (Recommended for Java)

Railway provides excellent Java/Spring Boot support:

1. **Create Railway Account**: Go to https://railway.app
2. **Connect Repository**: Link your GitHub repository
3. **Add Environment Variables**:
   - `JAVA_VERSION`: 21
   - `MAVEN_OPTS`: -Xmx1024m

4. **Railway automatically detects your Spring Boot app from pom.xml**

## Alternative: Heroku Deployment

1. **Create Heroku app**:
   ```bash
   heroku create your-app-name
   ```

2. **Add Java buildpack**:
   ```bash
   heroku buildpacks:set heroku/java
   ```

3. **Set environment variables**:
   ```bash
   heroku config:set JAVA_OPTS="-Xmx512m"
   ```

4. **Deploy**:
   ```bash
   git push heroku main
   ```

## Local Development

Start the backend:
```bash
mvn spring-boot:run
```

Backend will be available at: http://localhost:8080

### API Endpoints:
- GET /api/products
- POST /api/products 
- GET /api/products/{id}
- POST /api/products/{id}/reviews
- And more...

## Environment Configuration

Update `next.config.js` with your deployed backend URL:
```javascript
env: {
  NEXT_PUBLIC_API_URL: 'https://your-backend-url.railway.app'
}
```
