package com.example.flowerlab.controller;

import com.example.flowerlab.dto.FlowerBouquetRecommendationRequest;
import com.example.flowerlab.dto.FlowerBouquetRecommendationResponse;
import com.example.flowerlab.service.FlowerBouquetRecommendationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/flower-bouquets")
@RestController
public class FlowerBouquetRecommendationController {

    private final FlowerBouquetRecommendationServiceImpl flowerBouquetRecommendationService;

    @PostMapping("/recommendations")
    public ResponseEntity<FlowerBouquetRecommendationResponse> getFlowerRecommendation(@RequestBody FlowerBouquetRecommendationRequest request) {
        FlowerBouquetRecommendationResponse response = flowerBouquetRecommendationService.getRecommendation(request);
        return ResponseEntity.ok(response);
    }

}
