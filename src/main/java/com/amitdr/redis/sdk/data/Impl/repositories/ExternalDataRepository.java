package com.amitdr.redis.sdk.data.Impl.repositories;

import org.springframework.stereotype.Repository;

@Repository("external-repository")
public class ExternalDataRepository implements DataRepository {

    @Override
    public void set(Integer key, String value) {
    }

    @Override
    public String get(Integer key) {
        return "";
    }

    @Override
    public boolean exists(Integer key) {
        return true;
    }

    @Override
    public void delete(Integer key) {
    }
}
