package com.xiaoma.lrubitmap;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by xiaoma on 2017/5/15.
 */

public class DoubleCache implements ImageCache {

    public MemoryCache memoryCache;
    public DiskCache diskCache;

    public DoubleCache(Context context) {
        memoryCache = new MemoryCache();
        diskCache =DiskCache.getInstance(context);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if(bitmap==null){
            bitmap=diskCache.get(url);
            return bitmap;
        }
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url,bitmap);
        diskCache.put(url,bitmap);
    }

    public MemoryCache getMemoryCache() {
        return memoryCache;
    }

    public DiskCache getDiskCache() {
        return diskCache;
    }
}
