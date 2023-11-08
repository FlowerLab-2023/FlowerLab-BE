package com.example.flowerlab.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class FlowerBouquetRecommendationRequest {
    private String situation;
    private String mood;
    private String color;
}
