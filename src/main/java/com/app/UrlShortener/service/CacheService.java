package com.app.UrlShortener.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CacheService<K,V> extends LinkedHashMap<K,V> {
    private static final Integer capacity = 100;

    public CacheService() {
        super(capacity,0.75f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
