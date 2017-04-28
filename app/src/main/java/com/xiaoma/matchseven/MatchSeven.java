package com.xiaoma.matchseven;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaoma.base.BaseActivity;
import com.xiaoma.hometest.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoma on 2017/3/29.
 */

public class MatchSeven extends BaseActivity {

    @Bind(R.id.btn_camara)
    Button btnCamara;
    @Bind(R.id.btn_pics)
    Button btnPics;
    @Bind(R.id.iv_img)
    ImageView ivImg;
    private File imgFile;
    private File output;
    private String[] mPermissions;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_camara, R.id.btn_pics})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_camara:
                checkPermision();
                break;
            case R.id.btn_pics:
                break;
        }
    }

    private final static int REQUECT_CODE_SDCARD = 1009;
    
    private void goCamera() {
        imgFile = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!imgFile.getParentFile().exists()) {
            imgFile.getParentFile().mkdirs();
        }

        Uri imgUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            //如果是7.0或以上，使用getUriForFile()获取文件的Uri
            imgUri = FileProvider.getUriForFile(this, "com.sheyuan.picsprovider", imgFile);
        } else {
            imgUri = Uri.fromFile(imgFile);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, 1006);
    }

    private void bigSmall(File srcFile) {
        output = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!output.getParentFile().exists()) {
            output.getParentFile().mkdirs();
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(Uri.fromFile(srcFile), "image/*");
        intent.setDataAndType(getImageContentUri(this, srcFile), "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 480);

        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, 1007);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1006:
                bigSmall(imgFile);
                break;
            case 1007:
                Glide.with(this).load(output).into(ivImg);
                break;
        }
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }



    private void checkPermision() {
        mPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        List<String> deniedPermissions = findDeniedPermissions(mPermissions);
        if (deniedPermissions != null && deniedPermissions.size() > 0) {
            //申请多个对应的权限
            ActivityCompat.requestPermissions(this,
                    deniedPermissions.toArray(new String[]{}),
                    REQUECT_CODE_SDCARD);

        } else {
            //权限允许后自己的操作
            goCamera();
        }
    }


    //获取没有允许的权限
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> list = new ArrayList<>();
        for (String str : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    str) != PackageManager.PERMISSION_GRANTED) {
                list.add(str);
            }
        }
        return list;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUECT_CODE_SDCARD:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goCamera();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
