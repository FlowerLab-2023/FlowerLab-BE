package com.example.flowerlab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowerInfo {
    private String photoUrl;
    private String name;
    private String language;
    private String recommendationReason;
}
