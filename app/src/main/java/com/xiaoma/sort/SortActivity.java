package com.xiaoma.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoma.base.BaseActivity;
import com.xiaoma.hometest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoma on 2017/4/28.
 */

public class SortActivity extends BaseActivity {

    @Bind(R.id.btn_sort)
    Button btnSort;
    @Bind(R.id.tv_content)
    TextView tvContent;

    private int[] arr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);

        arr = new int[]{3,5,3,11,0,44,2,9,4,8,1};

    }

    @OnClick(R.id.btn_sort)
    public void onViewClicked() {

        quiteSort(arr,0,arr.length);
    }

    //快速排序法
    private void quiteSort(int[] arr,int start,int end) {

    }
}
