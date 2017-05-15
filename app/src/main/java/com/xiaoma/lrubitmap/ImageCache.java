package com.xiaoma.lrubitmap;

import android.graphics.Bitmap;

/**
 * Created by xiaoma on 2017/5/13.
 */

public interface ImageCache {

    Bitmap get(String url);

    void put(String url, Bitmap bitmap);
    
}
