package com.mumu.cache.api;

/**
 * 缓存详细信息
 *
 * @author mumu
 * @date 2022/3/26
 */
public interface ICacheEntry<K, V> {
    K key();
    
    V value();
}