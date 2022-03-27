package com.mumu.cache.core;

import com.mumu.cache.api.ICache;
import com.mumu.cache.api.ICacheContext;
import com.mumu.cache.api.ICacheEvict;
import com.mumu.cache.exception.CacheRuntimeException;
import com.mumu.cache.support.evict.CacheEvictContext;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author mumu
 * @date 2022/3/26
 */
public class Cache<K, V> implements ICache<K, V> {
    /**
     * map 信息
     */
    private Map<K, V> map;

    /**
     * 大小限制
     */
    private int sizeLimit;

    /**
     * 驱除策略
     */
    private ICacheEvict<K, V> cacheEvict;

    /**
     * 设置 map 实现
     *
     * @param map 实现
     * @return this
     */
    public Cache<K, V> map(Map<K, V> map) {
        this.map = map;
        return this;
    }

    /**
     * 设置大小限制
     *
     * @param sizeLimit 大小限制
     * @return this
     */
    public Cache<K, V> sizeLimit(int sizeLimit) {
        this.sizeLimit = sizeLimit;
        return this;
    }

    /**
     * 设置驱除策略
     *
     * @param cacheEvict 驱除策略
     * @return this
     */
    public Cache<K, V> evict(ICacheEvict<K, V> cacheEvict) {
        this.cacheEvict = cacheEvict;
        return this;
    }

    public Cache(ICacheContext<K, V> context) {
        this.map = context.map();
        this.sizeLimit = context.size();
        this.cacheEvict = context.cacheEvict();
    }

    @Override
    public ICacheEvict<K, V> evict() {
        return this.cacheEvict;
    }

    @Override
    public V put(K key, V value) {
        // 1. 尝试驱除
        CacheEvictContext<K, V> context = new CacheEvictContext<>();
        context.key(key).size(sizeLimit).cache(this);
        cacheEvict.evict(context);
        // 2. 判断是否已满
        if (isSizeLimit()) {
            throw new CacheRuntimeException("当前队列已满，数据添加失败！");
        }
        // 3. 添加
        return map.put(key, value);
    }

    private boolean isSizeLimit() {
        final int size = this.size();
        return size >= this.sizeLimit;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}