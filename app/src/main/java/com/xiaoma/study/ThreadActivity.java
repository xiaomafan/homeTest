package com.xiaoma.study;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiaoma on 2017/5/12.
 */

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        
      ThreadFactory threadFactory=  new ThreadFactory(){
          
         private final AtomicInteger mCount=new  AtomicInteger(1);
          
            @Override
            public Thread newThread(@NonNull Runnable r) {
                mCount.getAndIncrement();
                mCount.getAndDecrement();
                mCount.decrementAndGet();
                return new Thread(r);
            }
        };
    }
}
