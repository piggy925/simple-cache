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
}