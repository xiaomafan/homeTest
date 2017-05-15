package com.xiaoma.lrubitmap;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by xiaoma on 2017/5/13.
 */

public class DiskCache implements ImageCache {

    private static DiskCache mDiskCache;
    private DiskLruCache mDiskLruCache;
    private final String IMAGE_DISK_CACHE = "imgcache";
    private final int MB = 1024 * 1024;
    private final int DISK_CACHE_INDEX = 0;
    private MemoryCache memoryCache;

    private DiskCache(Context context) {
        initDiskCache(context);
    }
    

    public static DiskCache getInstance(Context context) {
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
            mDiskLruCache = DiskLruCache.open(cacheDir, getVersion(context), 1, 50 * MB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        memoryCache = new MemoryCache();
    }

    //获取版本信息
    private int getVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
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
        Bitmap bitmap = null;
        String key = urlUtils.hashKeyFormUrl(url);
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(DISK_CACHE_INDEX);
                bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        String key = urlUtils.hashKeyFormUrl(url);
        try {
            DiskLruCache.Editor edit = mDiskLruCache.edit(key);
            if (edit != null) {
                OutputStream outputStream = edit.newOutputStream(DISK_CACHE_INDEX);
                if (writeBitmapToDisk(bitmap, outputStream)) {
                    //成功下载
                    edit.commit();
                } else {
                    edit.abort();
                }
                mDiskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean writeBitmapToDisk(Bitmap bitmap, OutputStream outputStream) {
        BufferedOutputStream bos = new BufferedOutputStream(outputStream, 8 * 1024);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        boolean result = true;
        try {
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        } finally {
            IOUtils.closeQuickly(bos);
        }
        return result;
    }

}
