package com.foodcomparison.controller;

import com.foodcomparison.model.ApiResponse;
import com.foodcomparison.model.Dish;
import com.foodcomparison.service.SwiggyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller acts as API exposed from swiggy to
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/swiggy")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081", "http://localhost:4200"})
public class SwiggyController {

    private final SwiggyService swiggyService;

    public SwiggyController(SwiggyService swiggyService) {
        this.swiggyService = swiggyService;
    }

    /**
     * Search dishes by dish name
     * GET /api/v1/swiggy/search?dishName=Butter%20Chicken
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Dish>>> searchDishes(
            @RequestParam(name = "dishName") String dishName) {
        
        log.info("Swiggy search request for dish: {}", dishName);
        
        List<Dish> dishes = swiggyService.searchDishes(dishName);
        
        if (dishes.isEmpty()) {
            log.warn("No dishes found on Swiggy for: {}", dishName);
        }
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Search results from Swiggy",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Search by restaurant name
     * GET /api/v1/swiggy/search-restaurant?restaurantName=Maharaja%20Palace
     */
    @GetMapping("/search-restaurant")
    public ResponseEntity<ApiResponse<List<Dish>>> searchByRestaurant(
            @RequestParam(name = "restaurantName") String restaurantName) {
        
        log.info("Swiggy restaurant search for: {}", restaurantName);
        
        List<Dish> dishes = swiggyService.searchByRestaurant(restaurantName);
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Restaurant search results from Swiggy",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get all dishes
     * GET /api/v1/swiggy/all
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Dish>>> getAllDishes() {
        
        log.info("Fetching all dishes from Swiggy");
        
        List<Dish> dishes = swiggyService.getAllDishes();
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "All dishes from Swiggy",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get dish by ID
     * GET /api/v1/swiggy/dishes/{dishId}
     */
    @GetMapping("/dishes/{dishId}")
    public ResponseEntity<ApiResponse<Dish>> getDishById(
            @PathVariable String dishId) {
        
        log.info("Fetching Swiggy dish with ID: {}", dishId);
        
        Dish dish = swiggyService.getDishById(dishId);
        
        if (dish == null) {
            ApiResponse<Dish> response = new ApiResponse<>(
                    false,
                    HttpStatus.NOT_FOUND.value(),
                    "Dish not found on Swiggy",
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        ApiResponse<Dish> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Dish details from Swiggy",
                dish
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Filter by category
     * GET /api/v1/swiggy/category?category=North%20Indian
     */
    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<Dish>>> filterByCategory(
            @RequestParam(name = "category") String category) {
        
        log.info("Filtering Swiggy dishes by category: {}", category);
        
        List<Dish> dishes = swiggyService.filterByCategory(category);
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Filtered dishes by category from Swiggy",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Filter by vegetarian preference
     * GET /api/v1/swiggy/vegetarian?vegetarian=true
     */
    @GetMapping("/vegetarian")
    public ResponseEntity<ApiResponse<List<Dish>>> filterByVegetarian(
            @RequestParam(name = "vegetarian") Boolean vegetarian) {
        
        log.info("Filtering Swiggy dishes by vegetarian: {}", vegetarian);
        
        List<Dish> dishes = swiggyService.filterByVegetarian(vegetarian);
        
        ApiResponse<List<Dish>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Filtered dishes by vegetarian preference from Swiggy",
                dishes
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint
     * GET /api/v1/swiggy/health
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        
        ApiResponse<String> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Swiggy service is up and running",
                "OK"
        );
        
        return ResponseEntity.ok(response);
    }
}
