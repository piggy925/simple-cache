package com.mumu.cache.api;

/**
 * 驱除策略
 *
 * @author mumu
 * @date 2022 /3/26
 */
public interface ICacheEvictContext<K, V> {
    /**
     * 新加入元素的Key
     */
    K key();

    /**
     * Cache实现
     */
    ICache<K, V> cache();

    /**
     * 获取大小
     */
    int size();
}