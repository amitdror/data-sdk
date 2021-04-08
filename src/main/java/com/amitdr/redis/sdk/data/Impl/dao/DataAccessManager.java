package com.amitdr.redis.sdk.data.Impl.dao;

public interface DataAccessManager {

    void insert(Integer key, String value);

    void update(Integer key, String value);

    void upsert(Integer key, String value);

    void delete(Integer key);

    String select(Integer key);
}
