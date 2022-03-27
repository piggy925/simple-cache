package com.mumu.cache.support.evict;

import com.mumu.cache.api.ICache;
import com.mumu.cache.api.ICacheEvict;
import com.mumu.cache.api.ICacheEvictContext;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 缓存淘汰策略 - FIFO - 先进先出
 *
 * @author mumu
 * @date 2022/3/26
 */
public class CacheEvictFifo<K, V> implements ICacheEvict<K, V> {
    private Queue<K> queue = new LinkedList<>();

    @Override
    public void evict(ICacheEvictContext<K, V> context) {
        final ICache<K, V> cache = context.cache();
        // Cache大小超过限制，则移除最早加入的元素
        if (cache.size() >= context.size()) {
            K evictKey = queue.remove();
            cache.remove(evictKey);
        }
        // 未超过限制，则将该元素加入队尾
        final K key = context.key();
        queue.add(key);
    }

    @Override
    public void updateKey(K key) {

    }

    @Override
    public void removeKey(K key) {

    }
}