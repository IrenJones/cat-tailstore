package com.cattailstore.store.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.cattailstore.store.service.ItBookstoreService;
import org.springframework.stereotype.Service;

@Service
public class ItBookstoreServiceImpl implements ItBookstoreService {

    @Override
    public String getJsonInfoByIsbn(String isbn) {
        StringBuilder json = new StringBuilder();
        try {
            json = doRequest(new URL("https://api.itbook.store/1.0/books/" + isbn));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    @Override
    public String getJsonResultByTopic(String topic) {
        StringBuilder json = new StringBuilder();
        try {
            json = doRequest(new URL("https://api.itbook.store/1.0/search/" + topic));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    @Override
    public String getJsonResult() {
        return getJsonResultByTopic("new");
    }

    private StringBuilder doRequest(URL url) {
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
