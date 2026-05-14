package com.foodcomparison.controller;

import com.foodcomparison.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081", "http://localhost:4200"})
public class HealthController {

    /**
     * Health check endpoint
     * GET /api/v1/health
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, String>>> health() {
        
        Map<String, String> healthData = new HashMap<>();
        healthData.put("status", "UP");
        healthData.put("application", "Food Comparison App");
        healthData.put("version", "1.0.0");
        healthData.put("timestamp", System.currentTimeMillis() + "");
        
        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Application is healthy",
                healthData
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * Application info endpoint
     * GET /api/v1/info
     */
    @GetMapping("/info")
    public ResponseEntity<ApiResponse<Map<String, Object>>> info() {
        
        Map<String, Object> appInfo = new HashMap<>();
        appInfo.put("name", "Food Comparison App - MVP");
        appInfo.put("version", "1.0.0");
        appInfo.put("description", "Compare food prices between Swiggy and Zomato");
        appInfo.put("platforms", new String[]{"Swiggy", "Zomato"});
        appInfo.put("features", new String[]{
                "Search by dish name",
                "Filter by restaurant",
                "Filter by category",
                "Filter by vegetarian preference"
        });
        
        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Application information",
                appInfo
        );
        
        return ResponseEntity.ok(response);
    }
}
