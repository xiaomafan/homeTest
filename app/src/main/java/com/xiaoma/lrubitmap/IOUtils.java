package com.xiaoma.lrubitmap;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by xiaoma on 2017/5/15.
 */

public class IOUtils {

    public static void closeQuickly(Closeable... closeableList) {
        try {
            for (Closeable closeable : closeableList) {
                if (null != closeable) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
