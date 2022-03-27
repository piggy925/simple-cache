package com.mumu.cache.core;

import com.mumu.cache.api.ICacheContext;
import com.mumu.cache.api.ICacheEvict;

import java.util.Map;

/**
 * 缓存上下文
 *
 * @author mumu
 * @date 2022/3/27
 */
public class CacheContext<K, V> implements ICacheContext<K, V> {
    /**
     * Map信息
     */
    private Map<K, V> map;

    /**
     * 缓存大小限制
     */
    private int size;

    /**
     * 缓存淘汰策略
     */
    private ICacheEvict<K, V> evict;

    public CacheContext<K, V> map(Map<K, V> map) {
        this.map = map;
        return this;
    }

    public CacheContext<K, V> cacheEvict(ICacheEvict<K, V> evict) {
        this.evict = evict;
        return this;
    }

    public CacheContext<K, V> size(int size) {
        this.size = size;
        return this;
    }

    @Override
    public Map<K, V> map() {
        return map;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ICacheEvict<K, V> cacheEvict() {
        return evict;
    }
}