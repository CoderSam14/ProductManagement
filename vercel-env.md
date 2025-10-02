# Vercel Environment Variables

Set this environment variable in your Vercel dashboard:

## Required Environment Variable

**Name:** `NEXT_PUBLIC_API_URL`  
**Value:** Your backend API URL

### Examples:

**Development/Testing:**
```
NEXT_PUBLIC_API_URL=http://localhost:8080
```

**Production (Railway):**
```
NEXT_PUBLIC_API_URL=https://your-app-name.railway.app
```

**Production (Heroku):**
```
NEXT_PUBLIC_API_URL=https://your-app-name.herokuapp.com
```

## How to Set in Vercel:

1. Go to your Vercel project dashboard
2. Click on "Settings" tab
3. Click on "Environment Variables" in the sidebar
4. Add the variable:
   - **Name:** `NEXT_PUBLIC_API_URL`
   - **Value:** Your backend URL
   - **Environment:** Production (and Preview if needed)
5. Click "Save"
6. Redeploy your application
