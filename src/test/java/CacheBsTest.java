import com.mumu.cache.api.ICache;
import com.mumu.cache.bs.CacheBs;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mumu
 * @date 2022/3/27
 */
public class CacheBsTest {
    /**
     * 测试固定大小缓存
     */
    @Test
    public void testFixSize() {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .size(2)
                .build();

        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5", "5");
        cache.put("6", "6");

        Assert.assertEquals(2, cache.size());
        System.out.println(cache.keySet());
    }
}