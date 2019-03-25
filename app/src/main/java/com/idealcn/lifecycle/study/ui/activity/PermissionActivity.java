package com.idealcn.lifecycle.study.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public abstract class PermissionActivity extends AppCompatActivity {

    protected static final int REQUEST_PERMISSION_PHONE = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_permission);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.PHONE) == PackageManager.PERMISSION_DENIED){
            if (Build.VERSION_CODES.M>Build.VERSION.SDK_INT){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_PHONE_STATE,
                       // Manifest.permission.READ_PHONE_NUMBERS,  //API26
                        Manifest.permission.CALL_PHONE,
                      //  Manifest.permission.ANSWER_PHONE_CALLS,   //API26
                        Manifest.permission.ADD_VOICEMAIL,
                        Manifest.permission.USE_SIP
                }, REQUEST_PERMISSION_PHONE);
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission_group.PHONE}, REQUEST_PERMISSION_PHONE);
            }
        }else {

        }
        //请求多个权限
        //ActivityCompat.requestPermissions();
        //检查某一个权限
        //ActivityCompat.checkSelfPermission(this,"")
        //
        //ActivityCompat.shouldShowRequestPermissionRationale(this,"")
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

        }else {
            boolean rationale = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                rationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission_group.PHONE);
            }else {
                rationale =  ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_PHONE_STATE);
            }
            if (!rationale){
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("您需要同意这个权限")
                        .setTitle("权限申请提示")
                        .show();
            }
        }

    }
}
