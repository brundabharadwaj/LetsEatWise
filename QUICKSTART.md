# Quick Start Guide

## Run Locally (without Docker)

### Prerequisites
- Java 17+
- Maven 3.6+

### Steps
```bash
# 1. Clone the repo
git clone <your-repo-url>
cd food-comparison-app

# 2. Build the project
mvn clean install

# 3. Run the application
mvn spring-boot:run

# 4. Test it
curl http://localhost:8080/api/v1/health
```

---

## Run with Docker

### Prerequisites
- Docker
- Docker Compose

### Steps
```bash
# 1. Clone the repo
git clone <your-repo-url>
cd food-comparison-app

# 2. Build and run with Docker Compose
docker-compose up --build

# 3. Test it
curl http://localhost:8080/api/v1/health

# 4. Stop the containers
docker-compose down
```

---

## Test the API

### Search for a Dish
```bash
# Swiggy
curl "http://localhost:8080/api/v1/swiggy/search?dishName=Butter%20Chicken"

# Zomato
curl "http://localhost:8080/api/v1/zomato/search?dishName=Butter%20Chicken"
```

### Get All Dishes
```bash
# Swiggy
curl http://localhost:8080/api/v1/swiggy/all

# Zomato
curl http://localhost:8080/api/v1/zomato/all
```

### Filter by Category
```bash
# Swiggy - North Indian
curl "http://localhost:8080/api/v1/swiggy/category?category=North%20Indian"

# Zomato - North Indian
curl "http://localhost:8080/api/v1/zomato/category?category=North%20Indian"
```

### Get Vegetarian Dishes
```bash
# Swiggy
curl "http://localhost:8080/api/v1/swiggy/vegetarian?vegetarian=true"

# Zomato
curl "http://localhost:8080/api/v1/zomato/vegetarian?vegetarian=true"
```

---

## Directory Structure

```
food-comparison-app/
├── src/
│   ├── main/
│   │   ├── java/com/foodcomparison/
│   │   │   ├── controller/    # REST API controllers
│   │   │   ├── service/       # Business logic
│   │   │   ├── model/         # DTOs and entities
│   │   │   ├── exception/     # Exception handling
│   │   │   └── FoodComparisonApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/foodcomparison/
├── pom.xml                      # Maven dependencies
├── Dockerfile                   # Docker configuration
├── docker-compose.yml          # Docker Compose setup
├── .gitignore                  # Git ignore rules
├── .github/
│   └── workflows/
│       └── ci-cd.yml          # GitHub Actions CI/CD
└── README.md                    # Full documentation
```

---

## Available Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/health` | Health check |
| GET | `/api/v1/info` | Application info |
| GET | `/api/v1/swiggy/search` | Search dishes on Swiggy |
| GET | `/api/v1/swiggy/all` | Get all Swiggy dishes |
| GET | `/api/v1/swiggy/vegetarian` | Filter by veg preference |
| GET | `/api/v1/zomato/search` | Search dishes on Zomato |
| GET | `/api/v1/zomato/all` | Get all Zomato dishes |
| GET | `/api/v1/zomato/vegetarian` | Filter by veg preference |

---

## Common Issues & Solutions

### Port 8080 in Use
```bash
# Option 1: Use a different port
# Edit application.properties:
# server.port=8081

# Option 2: Kill the process using port 8080
lsof -ti:8080 | xargs kill -9
```

### Build Failures
```bash
# Clean build
mvn clean install -DskipTests

# Update Maven
mvn -v
```

### CORS Issues
- Frontend URL must be in `application.properties`
- Check `@CrossOrigin` annotations in controllers

---

## Next Steps

1. ✅ Backend MVP complete
2. ⬜ Build React frontend for comparison UI
3. ⬜ Add user authentication
4. ⬜ Implement real API integrations
5. ⬜ Deploy to cloud (AWS/Azure/GCP)

---

For detailed documentation, see **README.md**
