package com.foodcomparison.service;

import com.foodcomparison.model.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SwiggyService {

    private static final List<Dish> SWIGGY_DISHES = new ArrayList<>();

    static {
        // Initialize dummy data for Swiggy
        SWIGGY_DISHES.add(Dish.builder()
                .dishId("swg_001")
                .dishName("Butter Chicken")
                .restaurantName("Maharaja Palace")
                .category("North Indian")
                .price(320.0)
                .rating(4.5)
                .deliveryTimeMinutes(30)
                .description("Creamy butter chicken with spices")
                .vegetarian(false)
                .build());

        SWIGGY_DISHES.add(Dish.builder()
                .dishId("swg_002")
                .dishName("Paneer Tikka")
                .restaurantName("Indian Tadka")
                .category("Appetizers")
                .price(250.0)
                .rating(4.3)
                .deliveryTimeMinutes(25)
                .description("Grilled paneer with Indian spices")
                .vegetarian(true)
                .build());

        SWIGGY_DISHES.add(Dish.builder()
                .dishId("swg_003")
                .dishName("Biryani")
                .restaurantName("Hyderabadi Biryani House")
                .category("Biryani")
                .price(280.0)
                .rating(4.6)
                .deliveryTimeMinutes(35)
                .description("Authentic Hyderabadi biryani with meat")
                .vegetarian(false)
                .build());

        SWIGGY_DISHES.add(Dish.builder()
                .dishId("swg_004")
                .dishName("Dosa")
                .restaurantName("South Indian Delights")
                .category("South Indian")
                .price(150.0)
                .rating(4.4)
                .deliveryTimeMinutes(20)
                .description("Crispy dosa with sambar and chutney")
                .vegetarian(true)
                .build());

        SWIGGY_DISHES.add(Dish.builder()
                .dishId("swg_005")
                .dishName("Chole Bhature")
                .restaurantName("Punjab Express")
                .category("North Indian")
                .price(180.0)
                .rating(4.2)
                .deliveryTimeMinutes(28)
                .description("Soft bhature with spicy chole")
                .vegetarian(true)
                .build());

        SWIGGY_DISHES.add(Dish.builder()
                .dishId("swg_006")
                .dishName("Tikka Masala")
                .restaurantName("Maharaja Palace")
                .category("North Indian")
                .price(310.0)
                .rating(4.4)
                .deliveryTimeMinutes(32)
                .description("Tender chicken in creamy tomato sauce")
                .vegetarian(false)
                .build());

        SWIGGY_DISHES.add(Dish.builder()
                .dishId("swg_007")
                .dishName("Margherita Pizza")
                .restaurantName("Pizza Palace")
                .category("Continental")
                .price(350.0)
                .rating(4.3)
                .deliveryTimeMinutes(30)
                .description("Classic pizza with fresh mozzarella")
                .vegetarian(true)
                .build());

        SWIGGY_DISHES.add(Dish.builder()
                .dishId("swg_008")
                .dishName("Garlic Bread")
                .restaurantName("Pizza Palace")
                .category("Sides")
                .price(120.0)
                .rating(4.1)
                .deliveryTimeMinutes(15)
                .description("Crispy garlic bread")
                .vegetarian(true)
                .build());
    }

    /**
     * Search dishes on Swiggy by dish name (case-insensitive)
     */
    public List<Dish> searchDishes(String dishName) {
        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be empty");
        }

        return SWIGGY_DISHES.stream()
                .filter(dish -> dish.getDishName().toLowerCase()
                        .contains(dishName.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Search dishes by restaurant name
     */
    public List<Dish> searchByRestaurant(String restaurantName) {
        if (restaurantName == null || restaurantName.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name cannot be empty");
        }

        return SWIGGY_DISHES.stream()
                .filter(dish -> dish.getRestaurantName().toLowerCase()
                        .contains(restaurantName.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Get all dishes
     */
    public List<Dish> getAllDishes() {
        return new ArrayList<>(SWIGGY_DISHES);
    }

    /**
     * Get dish by ID
     */
    public Dish getDishById(String dishId) {
        return SWIGGY_DISHES.stream()
                .filter(dish -> dish.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Filter by category
     */
    public List<Dish> filterByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        return SWIGGY_DISHES.stream()
                .filter(dish -> dish.getCategory().toLowerCase()
                        .contains(category.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filter by vegetarian preference
     */
    public List<Dish> filterByVegetarian(Boolean vegetarian) {
        return SWIGGY_DISHES.stream()
                .filter(dish -> dish.getVegetarian().equals(vegetarian))
                .collect(Collectors.toList());
    }
}
