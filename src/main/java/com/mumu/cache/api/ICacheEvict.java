package com.mumu.cache.api;

/**
 * 驱除接口
 *
 * @author mumu
 * @date 2022/3/26
 */
public interface ICacheEvict<K, V> {
    /**
     * 驱除策略
     *
     * @param context 上下文
     * @return 被移除的明细，没有时返回 null
     */
    void evict(final ICacheEvictContext<K, V> context);

    /**
     * 更新 key 信息
     *
     * @param key key
     */
    void updateKey(final K key);

    /**
     * 删除 key 信息
     *
     * @param key key
     * @since 0.0.11
     */
    void removeKey(final K key);
}