package com.mumu.cache.core;

import com.mumu.cache.api.ICache;
import com.mumu.cache.api.ICacheEvict;
import com.mumu.cache.exception.CacheRuntimeException;
import com.mumu.cache.support.evict.CacheEvictContext;

import java.util.Map;

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
    private ICacheEvict<K, V> evict;

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
        this.evict = cacheEvict;
        return this;
    }

    @Override
    public ICacheEvict<K, V> evict() {
        return this.evict;
    }

    @Override
    public V put(K key, V value) {
        // 1. 尝试驱除
        CacheEvictContext<K, V> context = new CacheEvictContext<>();
        context.key(key).size(sizeLimit).cache(this);
        evict.evict(context);
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
}