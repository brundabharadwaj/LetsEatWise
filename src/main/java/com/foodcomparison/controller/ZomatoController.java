package com.foodcomparison.controller;

import com.foodcomparison.model.ApiResponse;
import com.foodcomparison.model.Dish;
import com.foodcomparison.service.ZomatoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/zomato")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081", "http://localhost:4200"})
public class ZomatoController {

    private final ZomatoService zomatoService;

    public ZomatoController(ZomatoService zomatoService) {
        this.zomatoService = zomatoService;
    }

    /**
     * Search dishes by dish name
     * GET /api/v1/zomato/search?dishName=Butter%20Chicken
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Dish>>> searchDishes(
            @RequestParam(name = "dishName") String dishName) {
        
        log.info("Zomato search request for dish: {}", dishName);
        
        List<Dish> dishes = zomatoService.searchDishes(dishName);
        
        if (dishes.isEmpty()) {
            log.warn("No dishes found on Zomato for: {}", dishName);
        }
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Search results from Zomato",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Search by restaurant name
     * GET /api/v1/zomato/search-restaurant?restaurantName=Royal%20Treat
     */
    @GetMapping("/search-restaurant")
    public ResponseEntity<ApiResponse<List<Dish>>> searchByRestaurant(
            @RequestParam(name = "restaurantName") String restaurantName) {
        
        log.info("Zomato restaurant search for: {}", restaurantName);
        
        List<Dish> dishes = zomatoService.searchByRestaurant(restaurantName);
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Restaurant search results from Zomato",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get all dishes
     * GET /api/v1/zomato/all
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Dish>>> getAllDishes() {
        
        log.info("Fetching all dishes from Zomato");
        
        List<Dish> dishes = zomatoService.getAllDishes();
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "All dishes from Zomato",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get dish by ID
     * GET /api/v1/zomato/dishes/{dishId}
     */
    @GetMapping("/dishes/{dishId}")
    public ResponseEntity<ApiResponse<Dish>> getDishById(
            @PathVariable String dishId) {
        
        log.info("Fetching Zomato dish with ID: {}", dishId);
        
        Dish dish = zomatoService.getDishById(dishId);
        
        if (dish == null) {
            ApiResponse<Dish> response = new ApiResponse<>(
                    false,
                    HttpStatus.NOT_FOUND.value(),
                    "Dish not found on Zomato",
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        ApiResponse<Dish> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Dish details from Zomato",
                dish
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Filter by category
     * GET /api/v1/zomato/category?category=North%20Indian
     */
    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<Dish>>> filterByCategory(
            @RequestParam(name = "category") String category) {
        
        log.info("Filtering Zomato dishes by category: {}", category);
        
        List<Dish> dishes = zomatoService.filterByCategory(category);
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Filtered dishes by category from Zomato",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Filter by vegetarian preference
     * GET /api/v1/zomato/vegetarian?vegetarian=true
     */
    @GetMapping("/vegetarian")
    public ResponseEntity<ApiResponse<List<Dish>>> filterByVegetarian(
            @RequestParam(name = "vegetarian") Boolean vegetarian) {
        
        log.info("Filtering Zomato dishes by vegetarian: {}", vegetarian);
        
        List<Dish> dishes = zomatoService.filterByVegetarian(vegetarian);
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Filtered dishes by vegetarian preference from Zomato",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint
     * GET /api/v1/zomato/health
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        
        ApiResponse<String> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Zomato service is up and running",
                "OK"
        );
        
        return ResponseEntity.ok(response);
    }
}
