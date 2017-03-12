package com.kevinbigler.mariomakertracker.common;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by Kevin on 3/11/2017.
 */
public class RestUtils {
    public ResponseEntity<String> getAsText(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("HTTP Status: " + response.getStatusCode().toString());

        System.out.println("Body:");
        System.out.println("---------------------------------");
        System.out.println(response.getBody());
        System.out.println("---------------------------------");

        return response;
    }
}
