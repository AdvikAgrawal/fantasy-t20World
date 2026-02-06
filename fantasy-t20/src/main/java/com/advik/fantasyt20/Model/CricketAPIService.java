package com.advik.fantasyt20.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CricketAPIService {
    
    @Value("${cricketApi.key}")
    private String apiKey;

    @Value("${cricketApi.host}")
    private String apiHost;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getUpcomingFixtures() {
        String url = "https://cricket-api-free-data.p.rapidapi.com/cricket-schedule-international";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", apiKey);
        headers.set("x-rapidapi-host", apiHost);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        }
        catch (Exception err) {
            return "{error: Could not fetch data: " + err.getMessage() + "}";
        }
    }
}
