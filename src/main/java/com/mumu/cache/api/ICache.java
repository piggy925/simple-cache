package com.mumu.cache.api;

import java.util.Map;

/**
 * 缓存接口
 *
 * @author mumu
 * @date 2022/3/26
 */
public interface ICache<K, V> extends Map<K, V> {
    /**
     * 淘汰策略
     *
     * @return 淘汰
     */
    ICacheEvict<K, V> evict();

    /**
     * Expire cache.
     *
     * @param key         the key
     * @param timeInMills 在多长时间后过期（单位为毫秒）
     * @return the cache
     */
    ICache<K, V> expire(final K key, final long timeInMills);

    /**
     * 在指定的时间过期
     *
     * @param key         the key
     * @param timeInMills 过期时间
     * @return the cache
     */
    ICache<K, V> expireAt(final K key, final long timeInMills);
}