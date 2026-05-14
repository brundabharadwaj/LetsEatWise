package com.foodcomparison.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    
    @JsonProperty("dish_id")
    private String dishId;
    
    @JsonProperty("dish_name")
    private String dishName;
    
    @JsonProperty("restaurant_name")
    private String restaurantName;
    
    @JsonProperty("category")
    private String category;
    
    @JsonProperty("price")
    private Double price;
    
    @JsonProperty("rating")
    private Double rating;
    
    @JsonProperty("delivery_time_minutes")
    private Integer deliveryTimeMinutes;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("vegetarian")
    private Boolean vegetarian;
}
