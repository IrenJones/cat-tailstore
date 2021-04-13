package com.cattailstore.store.service;

import java.util.List;
import java.util.Map;
import com.cattailstore.store.model.mongodb.BookFull;

public interface ItBookstoreService {

    String getJsonInfoByIsbn(String isbn);

    BookFull getInfo(String isbn);

    String getJsonResultByTopic(String topic);

    String getJsonResultByTopic(String topic, int page);

    List<Map<String, String>> getBookInfoByTopic(String topic);

    int getPageCount(String topic);

    String getJsonResult();
}
