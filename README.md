# Food Comparison App - MVP Backend

A Spring Boot backend application for comparing food prices between Swiggy and Zomato.

## Project Overview

This is the Week 1 MVP of a food delivery app comparison platform. It provides dummy APIs for Swiggy and Zomato that allow users to:
- Search dishes by name
- Filter by restaurant, category, and vegetarian preference
- Compare prices between platforms
- Access ratings and delivery times

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.2**
- **Spring Security**
- **Spring Data JPA**
- **H2 Database** (in-memory for MVP)
- **Maven**
- **JWT** (for future authentication)

## Project Structure

```
food-comparison-app/
├── src/main/java/com/foodcomparison/
│   ├── FoodComparisonApplication.java       # Main Spring Boot class
│   ├── controller/
│   │   ├── SwiggyController.java           # Swiggy API endpoints
│   │   ├── ZomatoController.java           # Zomato API endpoints
│   │   └── HealthController.java           # Health check endpoints
│   ├── service/
│   │   ├── SwiggyService.java              # Swiggy business logic with dummy data
│   │   └── ZomatoService.java              # Zomato business logic with dummy data
│   ├── model/
│   │   ├── Dish.java                       # Dish entity
│   │   └── ApiResponse.java                # Standardized API response wrapper
│   └── exception/
│       ├── ResourceNotFoundException.java   # Custom exception
│       └── GlobalExceptionHandler.java     # Global exception handler
├── src/main/resources/
│   └── application.properties               # Application configuration
├── pom.xml                                  # Maven dependencies
└── README.md                               # This file
```

## Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Git**

## Setup & Installation

### 1. Clone the Repository
```bash
git clone <your-github-repo-url>
cd food-comparison-app
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. Verify It's Running
```bash
curl http://localhost:8080/api/v1/health
```

You should see:
```json
{
  "success": true,
  "status_code": 200,
  "message": "Application is healthy",
  "data": {
    "status": "UP",
    "application": "Food Comparison App",
    "version": "1.0.0"
  },
  "timestamp": 1234567890
}
```

## API Endpoints

### Base URL
```
http://localhost:8080/api/v1
```

### Health & Info Endpoints

#### Health Check
```
GET /health
```

#### Application Info
```
GET /info
```

### Swiggy API Endpoints

All endpoints are under `/api/v1/swiggy`

#### 1. Search Dishes by Name
```
GET /swiggy/search?dishName=Butter Chicken
```

#### 2. Search by Restaurant
```
GET /swiggy/search-restaurant?restaurantName=Maharaja Palace
```

#### 3. Get All Dishes
```
GET /swiggy/all
```

#### 4. Get Dish by ID
```
GET /swiggy/dishes/{dishId}
Example: GET /swiggy/dishes/swg_001
```

#### 5. Filter by Category
```
GET /swiggy/category?category=North Indian
```

#### 6. Filter by Vegetarian Preference
```
GET /swiggy/vegetarian?vegetarian=true
```

#### 7. Health Check
```
GET /swiggy/health
```

### Zomato API Endpoints

All endpoints are under `/api/v1/zomato` (same structure as Swiggy)

#### 1. Search Dishes by Name
```
GET /zomato/search?dishName=Butter Chicken
```

#### 2. Search by Restaurant
```
GET /zomato/search-restaurant?restaurantName=Royal Treat
```

#### 3. Get All Dishes
```
GET /zomato/all
```

#### 4. Get Dish by ID
```
GET /zomato/dishes/{dishId}
Example: GET /zomato/dishes/zom_001
```

#### 5. Filter by Category
```
GET /zomato/category?category=North Indian
```

#### 6. Filter by Vegetarian Preference
```
GET /zomato/vegetarian?vegetarian=true
```

#### 7. Health Check
```
GET /zomato/health
```

## Sample Response Format

### Success Response
```json
{
  "success": true,
  "status_code": 200,
  "message": "Search results from Swiggy",
  "data": [
    {
      "dish_id": "swg_001",
      "dish_name": "Butter Chicken",
      "restaurant_name": "Maharaja Palace",
      "category": "North Indian",
      "price": 320.0,
      "rating": 4.5,
      "delivery_time_minutes": 30,
      "description": "Creamy butter chicken with spices",
      "vegetarian": false
    }
  ],
  "timestamp": 1234567890
}
```

### Error Response
```json
{
  "success": false,
  "status_code": 404,
  "message": "Dish not found on Swiggy",
  "data": null,
  "timestamp": 1234567890
}
```

## Dummy Data

The application comes with pre-loaded dummy data for 8 dishes each from Swiggy and Zomato:

**Dishes included:**
- Butter Chicken
- Paneer Tikka
- Biryani
- Dosa
- Chole Bhature
- Tikka Masala
- Margherita Pizza
- Garlic Bread

Each dish has slightly different prices and ratings between platforms to demonstrate the comparison feature.

## Testing Endpoints

### Using cURL

```bash
# Test Swiggy search
curl "http://localhost:8080/api/v1/swiggy/search?dishName=Butter"

# Test Zomato search
curl "http://localhost:8080/api/v1/zomato/search?dishName=Butter"

# Compare prices for the same dish
curl "http://localhost:8080/api/v1/swiggy/search?dishName=Paneer Tikka"
curl "http://localhost:8080/api/v1/zomato/search?dishName=Paneer Tikka"
```

### Using Postman

1. Import the collection or create new requests
2. Set method to GET
3. Use the endpoint URLs from the list above
4. Send the request
5. View the response

## Next Steps (Phase 2)

- [ ] Add user authentication with JWT
- [ ] Implement credential storage for Swiggy/Zomato accounts
- [ ] Add coupons and offers data
- [ ] Include delivery fees and taxes in comparison
- [ ] Add pagination for large result sets
- [ ] Create React frontend for comparison interface
- [ ] Migrate from H2 to PostgreSQL
- [ ] Add Docker support
- [ ] Deploy to cloud (AWS/Azure/GCP)

## Database

Currently using **H2 in-memory database**. Configuration can be accessed at:
```
http://localhost:8080/h2-console
```

Credentials:
- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (blank)

## Configuration

Edit `src/main/resources/application.properties` to customize:

- Server port
- JWT secret (change before production!)
- Database settings
- CORS origins
- Logging levels

## Error Handling

The application implements global exception handling with proper HTTP status codes:

- `200 OK` - Successful request
- `400 Bad Request` - Invalid parameters
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

## CORS Configuration

Currently allowing requests from:
- `http://localhost:3000` (React frontend)
- `http://localhost:8081` (Alternative frontend)
- `http://localhost:4200` (Angular frontend)

Update in `application.properties` or controller `@CrossOrigin` annotations as needed.

## Logging

Configured to log at DEBUG level for the application and INFO level for Spring framework. Useful for debugging:

```bash
# View logs
mvn spring-boot:run
```

## Future Enhancements

1. **Authentication**: Implement Spring Security with user credentials
2. **Database**: Migrate to PostgreSQL for persistent storage
3. **Real APIs**: Integrate with actual Swiggy/Zomato APIs when available
4. **Pagination**: Add pagination for large result sets
5. **Caching**: Implement Redis for performance
6. **Coupons**: Track and apply discount coupons
7. **Notifications**: Push notifications for price drops
8. **Analytics**: Track user searches and comparisons
9. **Mobile App**: Build native mobile applications

## Contributing

When adding new features:

1. Create a feature branch
2. Follow the existing code structure
3. Add proper exception handling
4. Write unit tests
5. Submit a pull request

## Deployment

### Docker
```bash
docker build -t food-comparison-app .
docker run -p 8080:8080 food-comparison-app
```

### GitHub Actions (CI/CD)
Set up automated testing and deployment workflows.

## Troubleshooting

### Port 8080 Already in Use
```bash
# Change port in application.properties
server.port=8081
```

### Build Fails
```bash
# Clean and rebuild
mvn clean install -DskipTests
```

### CORS Issues
- Verify the frontend URL is in the allowed origins list
- Check the `@CrossOrigin` annotations in controllers

## License

MIT License - Feel free to use this for your portfolio and learning.

## Support

For issues or questions, create a GitHub issue or contact the maintainer.

---

**Happy coding! 🚀**

This is a learning project. Feel free to modify, enhance, and deploy to showcase your backend development skills.
