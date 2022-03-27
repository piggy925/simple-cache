package com.mumu.cache.api;

import java.util.Map;

/**
 * 缓存上下文
 *
 * @author mumu
 * @date 2022/3/27
 */
public interface ICacheContext<K, V> {
    /**
     * Map信息
     */
    Map<K, V> map();

    /**
     * 缓存大小限制
     */
    int size();

    /**
     * 缓存淘汰策略
     */
    ICacheEvict<K, V> cacheEvict();
}