package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;



public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    ImageButton btn;
    String number,stuname,distress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        number=intent.getStringExtra("number");
        stuname=intent.getStringExtra("stuname");
        distress=intent.getStringExtra("distress");

        //初始化相机权限
        ZXingLibrary.initDisplayOpinion(this);
        btn = findViewById(R.id.imageButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断手机版本是否在6.0以上，如果在6.0以上则需要动态申请权限
                if (Build.VERSION.SDK_INT > 22) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //先判断有没有权限 ，没有就在这里进行权限的申请
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                    } else {
                        //说明已经获取到摄像头权限了 想干嘛干嘛
                        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                        startActivityForResult(intent, 1);
                    }
                } else {
                    //这个说明系统版本在6.0之下，不需要动态获取权限。
                    //Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    //startActivityForResult(intent, 1);
                }
            }
        });

        btn = findViewById(R.id.imageButton2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InformActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("number",number);
                intent.putExtra("stuname",stuname);
                intent.putExtra("distress",distress);
                startActivity(intent);
            }
        }
    }
}
