package com.mumu.cache.api;

import java.util.Collection;

/**
 * 缓存过期
 *
 * @author mumu
 * @date 2022/4/4
 */
public interface ICacheExpire<K, V> {
    /**
     * 在指定时间过期
     *
     * @param key      the key
     * @param expireAt 过期时间
     */
    void expire(final K key, final long expireAt);

    /**
     * 惰性删除需要处理的key
     *
     * @param keyList the key list
     */
    void refreshExpire(final Collection<K> keyList);
}