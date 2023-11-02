package com.example.flowerlab.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FlowerBouquetRecommendationResponse {
    private String bouquetImageUrl;
    private List<FlowerInfo> flowers;
    private String cardMessage;
}