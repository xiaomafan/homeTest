package com.xiaoma.lrubitmap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xiaoma on 2017/5/15.
 */

public class urlUtils {

    public static String hashKeyFormUrl(String url) {
        String cacheKey = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            cacheKey = bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return cacheKey;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                builder.append('0');
            }
            builder.append(hex);
        }
        return builder.toString();
    }
}
