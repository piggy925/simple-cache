package com.mumu.cache.bs;


import com.github.houbb.heaven.util.common.ArgUtil;
import com.mumu.cache.api.ICache;
import com.mumu.cache.api.ICacheEvict;
import com.mumu.cache.core.Cache;
import com.mumu.cache.core.CacheContext;
import com.mumu.cache.support.evict.CacheEvicts;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存引导类
 *
 * @author mumu
 * @date 2022/3/26
 */
public final class CacheBs<K, V> {
    private CacheBs() {
    }

    /**
     * 创建对象实例
     */
    public static <K, V> CacheBs<K, V> newInstance() {
        return new CacheBs<>();
    }

    /**
     * map 实现
     */
    private Map<K, V> map = new HashMap<>();

    /**
     * 大小限制
     */
    private int size = Integer.MAX_VALUE;

    /**
     * 驱除策略
     */
    private ICacheEvict<K, V> evict = CacheEvicts.fifo();

    /**
     * map 实现
     *
     * @param map map
     * @return this
     * @since 0.0.2
     */
    public CacheBs<K, V> map(Map<K, V> map) {
        ArgUtil.notNull(map, "map");

        this.map = map;
        return this;
    }

    /**
     * 设置 size 信息
     *
     * @param size size
     * @return this
     * @since 0.0.2
     */
    public CacheBs<K, V> size(int size) {
        ArgUtil.notNegative(size, "size");

        this.size = size;
        return this;
    }

    /**
     * 设置驱除策略
     *
     * @param evict 驱除策略
     * @return this
     * @since 0.0.2
     */
    public CacheBs<K, V> evict(ICacheEvict<K, V> evict) {
        this.evict = evict;
        return this;
    }

    /**
     * 构建缓存信息
     *
     * @return 缓存信息
     * @since 0.0.2
     */
    public ICache<K, V> build() {
        CacheContext<K, V> context = new CacheContext<>();
        context.cacheEvict(evict);
        context.map(map);
        context.size(size);

        return new Cache<>(context);
    }
}