package com.amitdr.redis.sdk.data.Impl.dao;

import com.amitdr.redis.sdk.data.Impl.api.DataAccessLayer;
import com.amitdr.redis.sdk.data.Impl.exceptions.KeyNotFoundException;
import com.amitdr.redis.sdk.data.Impl.repositories.DataRepository;
import com.amitdr.redis.sdk.data.Impl.repositories.InMemoryDataRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TransactionalDataAccessManager implements DataAccessLayer {

    private final DataRepository dataRepository;
    private final Set<Integer> changedEntities;
    private DataRepository tempRepository;

    public TransactionalDataAccessManager(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.changedEntities = new HashSet<>();
        this.tempRepository = null;
    }

    @Override
    public void insert(Integer key, String value) {
        if (!tempRepository.exists(key)) tempRepository.set(key, value);
    }

    @Override
    public void update(Integer key, String value) {
        if (tempRepository.exists(key)) tempRepository.set(key, value);
    }

    @Override
    public void upsert(Integer key, String value) {
        tempRepository.set(key, value);
    }

    @Override
    public void delete(Integer key) {
        tempRepository.delete(key);
    }

    @Override
    public String select(Integer key) {
        if (!tempRepository.exists(key)) throw new KeyNotFoundException();
        return tempRepository.get(key);
    }

    @Override
    public void beginTransaction() {
        if (tempRepository != null) tempRepository = cloneRepository(dataRepository);
    }

    @Override
    public void commit() {
        changedEntities.forEach(this::copyValueToProductionDatabase);
        rollback();
    }

    @Override
    public void rollback() {
        tempRepository = null;
        changedEntities.clear();
    }

    private void copyValueToProductionDatabase(Integer key) {
        if (tempRepository.exists(key)) dataRepository.set(key, tempRepository.get(key));
        dataRepository.delete(key);
    }

    private DataRepository cloneRepository(DataRepository dataRepository) {
        //todo: Impl repository copy
        return new InMemoryDataRepository();
    }
}
