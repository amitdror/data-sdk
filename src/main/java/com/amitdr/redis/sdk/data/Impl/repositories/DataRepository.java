package com.amitdr.redis.sdk.data.Impl.repositories;

public interface DataRepository {

    void set(Integer key, String value);

    String get(Integer key);

    boolean exists(Integer key);

    void delete(Integer key);
}
