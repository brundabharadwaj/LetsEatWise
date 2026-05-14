package com.foodcomparison.service;

import com.foodcomparison.model.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZomatoService {

    private static final List<Dish> ZOMATO_DISHES = new ArrayList<>();

    static {
        // Initialize dummy data for Zomato
        ZOMATO_DISHES.add(Dish.builder()
                .dishId("zom_001")
                .dishName("Butter Chicken")
                .restaurantName("Royal Treat")
                .category("North Indian")
                .price(299.0)
                .rating(4.6)
                .deliveryTimeMinutes(28)
                .description("Rich and creamy butter chicken")
                .vegetarian(false)
                .build());

        ZOMATO_DISHES.add(Dish.builder()
                .dishId("zom_002")
                .dishName("Paneer Tikka")
                .restaurantName("Spice Route")
                .category("Appetizers")
                .price(240.0)
                .rating(4.4)
                .deliveryTimeMinutes(22)
                .description("Chargrilled paneer cubes with spices")
                .vegetarian(true)
                .build());

        ZOMATO_DISHES.add(Dish.builder()
                .dishId("zom_003")
                .dishName("Biryani")
                .restaurantName("Biryani Corner")
                .category("Biryani")
                .price(300.0)
                .rating(4.7)
                .deliveryTimeMinutes(40)
                .description("Fragrant basmati biryani")
                .vegetarian(false)
                .build());

        ZOMATO_DISHES.add(Dish.builder()
                .dishId("zom_004")
                .dishName("Dosa")
                .restaurantName("Chennai Express")
                .category("South Indian")
                .price(160.0)
                .rating(4.5)
                .deliveryTimeMinutes(18)
                .description("Golden crispy dosa with podi")
                .vegetarian(true)
                .build());

        ZOMATO_DISHES.add(Dish.builder()
                .dishId("zom_005")
                .dishName("Chole Bhature")
                .restaurantName("Punjabi Dhaba")
                .category("North Indian")
                .price(175.0)
                .rating(4.3)
                .deliveryTimeMinutes(25)
                .description("Fluffy bhature with chickpea curry")
                .vegetarian(true)
                .build());

        ZOMATO_DISHES.add(Dish.builder()
                .dishId("zom_006")
                .dishName("Tikka Masala")
                .restaurantName("Royal Treat")
                .category("North Indian")
                .price(330.0)
                .rating(4.5)
                .deliveryTimeMinutes(30)
                .description("Succulent chicken in aromatic sauce")
                .vegetarian(false)
                .build());

        ZOMATO_DISHES.add(Dish.builder()
                .dishId("zom_007")
                .dishName("Margherita Pizza")
                .restaurantName("Pizzeria Italia")
                .category("Continental")
                .price(375.0)
                .rating(4.4)
                .deliveryTimeMinutes(32)
                .description("Authentic Italian margherita pizza")
                .vegetarian(true)
                .build());

        ZOMATO_DISHES.add(Dish.builder()
                .dishId("zom_008")
                .dishName("Garlic Bread")
                .restaurantName("Pizzeria Italia")
                .category("Sides")
                .price(130.0)
                .rating(4.2)
                .deliveryTimeMinutes(16)
                .description("Herb-infused garlic bread")
                .vegetarian(true)
                .build());
    }

    /**
     * Search dishes on Zomato by dish name (case-insensitive)
     */
    public List<Dish> searchDishes(String dishName) {
        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be empty");
        }

        return ZOMATO_DISHES.stream()
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

        return ZOMATO_DISHES.stream()
                .filter(dish -> dish.getRestaurantName().toLowerCase()
                        .contains(restaurantName.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Get all dishes
     */
    public List<Dish> getAllDishes() {
        return new ArrayList<>(ZOMATO_DISHES);
    }

    /**
     * Get dish by ID
     */
    public Dish getDishById(String dishId) {
        return ZOMATO_DISHES.stream()
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

        return ZOMATO_DISHES.stream()
                .filter(dish -> dish.getCategory().toLowerCase()
                        .contains(category.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filter by vegetarian preference
     */
    public List<Dish> filterByVegetarian(Boolean vegetarian) {
        return ZOMATO_DISHES.stream()
                .filter(dish -> dish.getVegetarian().equals(vegetarian))
                .collect(Collectors.toList());
    }
}
