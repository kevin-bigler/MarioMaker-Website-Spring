package com.kevinbigler.mariomakertracker.common;

import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Kevin on 3/11/2017.
 */
public class RestUtils {

    public ResponseEntity<String> getAsTextHtml(String url) {
        System.out.println("getAsTextHtml(): " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("HTTP Status: " + response.getStatusCode().toString());

//        System.out.println("Body:");
//        System.out.println("---------------------------------");
//        System.out.println(response.getBody());
//        System.out.println("---------------------------------");

        return response;
    }

    // TODO use something like this for getting images
    private ResponseEntity<byte[]> getAsFile(String url) {
        String fileNameOriginal = url.substring(url.lastIndexOf("/") + 1);
        String extension = url.substring(url.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String fileNameGenerated = uuid + extension;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET, entity, byte[].class);

        System.out.println("HTTP Status: " + response.getStatusCode().toString());

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                Files.write(Paths.get(fileNameGenerated), response.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // TODO log the error. handle error (halt execution?)
        }

        return response;
    }
}
