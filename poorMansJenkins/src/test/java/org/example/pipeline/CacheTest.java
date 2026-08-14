package org.example.pipeline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {
    private Cache cache;
    private Cache emptyCache;

    @BeforeEach
    void setup() {
        cache = new Cache();
        emptyCache = new Cache();

        cache.cacheValue("1", "a");
    }

    @Test
    void cacheCreatedEmpty() {
        assertTrue(emptyCache.isEmpty());
    }

    @Test
    void cacheValueThrowsIllArgError() {
        assertThrows(IllegalArgumentException.class,
                () -> cache.cacheValue(null, new Object()), "Expected IllegalArgumentException if key is null");

        assertThrows(IllegalArgumentException.class,
                () -> cache.cacheValue(new Object(), null), "Expected IllegalArgumentException if value is null");

        assertThrows(IllegalArgumentException.class,
                () -> cache.cacheValue(null, null), "Expected IllegalArgumentException if both key and value are null");
    }

    @Test
    void getCachedValThrowsIllArgErrWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> cache.getCachedValue(null), "Expected IllegalArgumentException when getCachedValue is passed null");
    }

    @Test
    void putAndGetKVpair() {
        assertEquals("a", cache.getCachedValue("1"), "Cached value 'a' at key 1, expected return 'a'");
    }

    @Test
    void cacheValueOverwritesValueAtExistingKey() {
        cache.cacheValue("1", "b");

        assertEquals("b", cache.getCachedValue("1"), "Overwritten value 'a' at key 1 with value 'b'. Expected return 'b'");
    }

    @Test
    void containsThrowsIllArgErrWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> cache.containsKey(null), "Expected IllegalArgumentException when containsKey is passed null");
    }

    @Test
    void containsKeyReturnsProperBool() {
        assertTrue(cache.containsKey("1"));
        assertFalse(cache.containsKey("2"));
    }

    @Test
    void clearEmptiesCache() {
        cache.clear();

        assertTrue(cache.isEmpty());
        assertFalse(cache.containsKey("1"));
    }

    @Test
    void isEmptyReturnsProperBool() {
        assertTrue(emptyCache.isEmpty());
        assertFalse(cache.isEmpty());
    }
}
