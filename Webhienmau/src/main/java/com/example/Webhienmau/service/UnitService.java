package com.example.Webhienmau.service;

import com.example.Webhienmau.dto.ApiResponse;
import com.example.Webhienmau.model.Unit;
import com.example.Webhienmau.repository.UnitRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {
    private static final String API_URL = "https://api.giotmauvang.org.vn/public/unit";
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UnitRepository unitRepository;

    @EventListener(ApplicationReadyEvent.class) // TỰ ĐỘNG CHẠY KHI SERVER KHỞI ĐỘNG
    public void fetchAndSaveUnitsOnStartup() {
        System.out.println("🚀 Fetching donation units on startup...");

        ApiResponse response = fetchAndSaveDonationUnits();

        System.out.println("✅ " + response.getMessage());
    }
    public ApiResponse fetchAndSaveDonationUnits() {
        ApiResponse response = new ApiResponse();

        try {
            // 1️⃣ Gửi request GET đến API
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> apiResponse = restTemplate.exchange(
                    API_URL, HttpMethod.GET, entity, String.class
            );

            // 2️⃣ Parse JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(apiResponse.getBody());
            JsonNode unitsJson = root.path("data");

            if (!unitsJson.isArray()) {
                response.setCode(404);
                response.setMessage("No donation units found from API.");
                return response;
            }

            List<Unit> unitsToSave = new ArrayList<>();
            for (JsonNode unitJson : unitsJson) {
                Unit unit = mapJsonToDonationUnit(unitJson);
                unitsToSave.add(unit);
            }

            // 3️⃣ Lưu dữ liệu vào database
            unitRepository.saveAll(unitsToSave);

            response.setCode(200);
            response.setMessage("Fetched and saved " + unitsToSave.size() + " donation units.");
            response.setData(unitsToSave);
            return response;

        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Error fetching donation units: " + e.getMessage());
            return response;
        }
    }

    private Unit mapJsonToDonationUnit(JsonNode jsonNode) {
        Unit unit = new Unit();
        unit.setUnitId(jsonNode.get("unit").asText());
        unit.setName(jsonNode.get("donatePlace").asText());
        unit.setDonateAddress(jsonNode.get("donateAddress").asText());
//        unit.set(jsonNode.get("donatePlace_EN").asText());
        return unit;
    }
}
