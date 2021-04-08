package com.amitdr.redis.sdk.data.Impl.dao;

import com.amitdr.redis.sdk.data.Impl.exceptions.KeyNotFoundException;
import com.amitdr.redis.sdk.data.Impl.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonTransactionalDataAccessManager implements DataAccessManager {

    private final DataRepository dataRepository;

    @Autowired
    public NonTransactionalDataAccessManager(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void insert(Integer key, String value) {
        if (dataRepository.exists(key)) throw new KeyNotFoundException();
        dataRepository.set(key, value);
    }

    public void update(Integer key, String value) {
        if (!dataRepository.exists(key)) throw new KeyNotFoundException();
        dataRepository.set(key, value);
    }

    public void upsert(Integer key, String value) {
        dataRepository.set(key, value);
    }

    public void delete(Integer key) {
        if (dataRepository.exists(key))
            dataRepository.delete(key);
    }

    public String select(Integer key) {
        if (!dataRepository.exists(key)) throw new KeyNotFoundException();
        return dataRepository.get(key);
    }
}
