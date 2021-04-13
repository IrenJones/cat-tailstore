package com.cattailstore.store.service;

public interface ItBookstoreService {

    String getJsonInfoByIsbn(String isbn);

    String getJsonResultByTopic(String topic);

    String getJsonResult();
}
