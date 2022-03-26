package com.mumu.cache.support.evict;

import com.mumu.cache.api.ICacheEvict;

/**
 * 缓存丢弃策略
 *
 * @author mumu
 * @date 2022/3/26
 */
public final class CacheEvicts {
    private CacheEvicts() {

    }

    /**
     * 先进先出
     */
    public static <K, V> ICacheEvict<K, V> fifo() {
        return new CacheEvictFifo<>();
    }

}