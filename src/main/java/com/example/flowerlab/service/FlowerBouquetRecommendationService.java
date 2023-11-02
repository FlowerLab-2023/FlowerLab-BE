package com.example.flowerlab.service;

import com.example.flowerlab.dto.FlowerBouquetRecommendationRequest;
import com.example.flowerlab.dto.FlowerBouquetRecommendationResponse;

public interface FlowerBouquetRecommendationService {
    FlowerBouquetRecommendationResponse getRecommendation(FlowerBouquetRecommendationRequest request);
}
