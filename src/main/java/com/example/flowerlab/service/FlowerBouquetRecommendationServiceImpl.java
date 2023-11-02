package com.example.flowerlab.service;

import com.example.flowerlab.dto.FlowerBouquetRecommendationRequest;
import com.example.flowerlab.dto.FlowerBouquetRecommendationResponse;
import com.example.flowerlab.exception.RecommendationException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlowerBouquetRecommendationServiceImpl implements FlowerBouquetRecommendationService {

    @Value("${flowerlab.script.path}")
    private String scriptPath;

    public FlowerBouquetRecommendationResponse getRecommendation(FlowerBouquetRecommendationRequest request) {
        ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath,
                request.getSituation());  // TODO 추후 request 객체로 수정
        processBuilder.redirectErrorStream(true);

        StringBuilder output = new StringBuilder();
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            process.waitFor();

            log.info("output = {}", output);

            int jsonStartIdx = output.indexOf("{");
            if (jsonStartIdx == -1) {
                throw new IllegalStateException("No JSON object found in output");
            }
            String jsonStr = output.substring(jsonStartIdx);
            JSONObject json = new JSONObject(jsonStr);

            FlowerBouquetRecommendationResponse response = FlowerBouquetRecommendationResponse.builder()
                    .bouquetImageUrl(json.getString("imageUrl"))
                    // TODO 추후 다른 필드들도 빌드
                    .build();

            return response;
        } catch (IllegalStateException e) {
            log.error("Error while parsing JSON response: ", e);
            throw new RecommendationException("Error while parsing JSON response", e);
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new RecommendationException("An unexpected error occurred", e);
        }
    }
}