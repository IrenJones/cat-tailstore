package com.cattailstore.store.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.cattailstore.store.model.mongodb.BookFull;
import com.cattailstore.store.service.ItBookstoreService;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;

/**
 * IT Bookstore is the supporting API for IT Bookstore, a California-based IT,
 * Programming, and Computer Science bookseller.
 * It allows you to search their database for available books.
 * https://api.itbook.store/#api-overview
 */

@Service
public class ItBookstoreServiceImpl implements ItBookstoreService {

    private final static String SITE_URL = "https://api.itbook.store/1.0/";

    @Override
    public String getJsonInfoByIsbn(String isbn) {
        StringBuilder json = new StringBuilder();
        try {
            json = doRequest(new URL(SITE_URL+ "books/" + isbn));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    @Override
    public BookFull getInfo(String isbn) {
        String json = getJsonInfoByIsbn(isbn);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> info = parser.parseMap(json);

        BookFull bookFull = new BookFull();
        bookFull.setTitle((String)info.get("title"));
        bookFull.setSubtitle((String)info.get("subtitle"));
        bookFull.setYear(Integer.parseInt((String)info.get("year")));
        bookFull.setAuthor((String)info.get("authors"));
        bookFull.setDescription((String)info.get("desc"));
        bookFull.setIsbn(isbn);

        return bookFull;
    }

    @Override
    public String getJsonResultByTopic(String topic) {
        return getJsonResultByTopic(topic, 1);
    }

    @Override
    public String getJsonResultByTopic(String topic, int page) {
        StringBuilder json = new StringBuilder();
        try {
            json = doRequest(new URL(SITE_URL + "search/" + topic + "/" + page));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    @Override
    public List<Map<String, String>> getBookInfoByTopic(String topic) {
        int pages = getPageCount(topic);
        List<Map<String, String>> books = new ArrayList<>();

        for (int page = 1; page <= pages; page++) {
            String json = getJsonResultByTopic(topic, page);
            JsonParser parser = JsonParserFactory.getJsonParser();
            Map<String, Object> info = parser.parseMap(json);
            books.addAll((List<Map<String, String>>)info.get("books"));
        }

        return books;
    }

    @Override
    public int getPageCount(String topic) {
        String json = getJsonResultByTopic(topic);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> info = parser.parseMap(json);
        return (int) Math.ceil(Integer.parseInt((String)info.get("total")) * 1.0 / 10);
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
