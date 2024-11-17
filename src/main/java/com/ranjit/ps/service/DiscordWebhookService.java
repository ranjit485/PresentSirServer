package com.ranjit.ps.service;

import com.ranjit.ps.model.DiscordMessage;
import com.ranjit.ps.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class DiscordWebhookService {

    private final String discordWebhookUrl = "https://discord.com/api/webhooks/1306848294600839270/W9GITB46e9VRZ9n8_ecYs2jPmAVgWDDAIS1lS3ylVTup_T5xY01Vs8GPvNqlxTYogKRD";

    public void sendDiscordMessage(String  message) {
        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create the payload
        DiscordMessage discordMessage = new DiscordMessage();

        discordMessage.setContent(message);
        discordMessage.setUsername("Controller");
        discordMessage.setAvatar_url("https://images.nightcafe.studio/jobs/f3wkutzS1a7Ne29k8dzC/f3wkutzS1a7Ne29k8dzC--1--w33a3.jpg?tr=w-1600,c-at_max");

        // Create the request
        HttpEntity<DiscordMessage> request = new HttpEntity<>(discordMessage, headers);

        // Send the POST request
        ResponseEntity<String> response = restTemplate.exchange(discordWebhookUrl, HttpMethod.POST, request, String.class);

        System.out.println("Response: " + response.getBody());
    }
}
