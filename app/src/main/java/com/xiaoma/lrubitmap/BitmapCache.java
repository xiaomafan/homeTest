package com.xiaoma.lrubitmap;

import android.graphics.Bitmap;

import java.io.IOException;

/**
 * Created by xiaoma on 2017/5/13.
 */

public interface BitmapCache {

    Bitmap get(String url) throws IOException;

    void put(String url, Bitmap bitmap);
    
}
