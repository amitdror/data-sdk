package com.amitdr.redis.sdk.data.Impl.repositories;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("in-memory-repository")
public class InMemoryDataRepository implements DataRepository {

    private final Map<Integer, String> database = new HashMap<>();

    @Override
    public void set(Integer key, String value) {
        database.put(key, value);
    }

    @Override
    public String get(Integer key) {
        return database.getOrDefault(key, null);
    }

    @Override
    public boolean exists(Integer key) {
        return this.get(key) != null;
    }

    @Override
    public void delete(Integer key) {
        database.remove(key);
    }
}
