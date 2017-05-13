package com.xiaoma.lrubitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by xiaoma on 2017/5/13.
 */

public class DiskCache implements BitmapCache {

    private DiskCache mDiskCache;
    private DiskLruCache mDiskLurCache;
    private final String IMAGE_DISK_CACHE = "imgcache";
    private final int MB = 1024 * 1024;
    private final int DISK_CACHE_INDEX = 0;

    private DiskCache(Context context) {
        initDiskCache(context);
    }

    public DiskCache getInstance(Context context) {
        if (mDiskCache == null) {
            synchronized (DiskCache.class) {
                if (mDiskCache == null) {
                    mDiskCache = new DiskCache(context.getApplicationContext());
                }
            }
        }
        return mDiskCache;
    }

    private void initDiskCache(Context context) {
        File cacheDir = getDiskCacheDir(context, IMAGE_DISK_CACHE);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        try {
            mDiskLurCache = DiskLruCache.open(cacheDir, 1, 1, 50 * MB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getDiskCacheDir(Context context, String image_disk_cache) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + IMAGE_DISK_CACHE);
    }

    @Override
    public Bitmap get(String url) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLurCache.get(url);
            if (snapshot != null) {
                FileInputStream inputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
                FileDescriptor fileDescriptor = inputStream.getFD();
                Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        
    }
}
