package com.xiaoma.lrubitmap;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by xiaoma on 2017/5/13.
 */

public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        final int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        final int cacheMemory = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        if (get(url) == null) {
            mMemoryCache.put(url, bitmap);
        }
    }
}
