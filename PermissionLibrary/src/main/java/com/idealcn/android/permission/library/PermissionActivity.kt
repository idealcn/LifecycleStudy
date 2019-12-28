package com.idealcn.android.permission.library

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission_group.PHONE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            if (Build.VERSION_CODES.M > Build.VERSION.SDK_INT) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_PHONE_STATE,  // Manifest.permission.READ_PHONE_NUMBERS,  //API26
                        Manifest.permission.CALL_PHONE,  //  Manifest.permission.ANSWER_PHONE_CALLS,   //API26
                        Manifest.permission.ADD_VOICEMAIL,
                        Manifest.permission.USE_SIP
                    ),
                    REQUEST_PERMISSION_PHONE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission_group.PHONE),
                    REQUEST_PERMISSION_PHONE
                )
            }
        } else {
        }
        //请求多个权限
//ActivityCompat.requestPermissions();
//检查某一个权限
//ActivityCompat.checkSelfPermission(this,"")
//
//ActivityCompat.shouldShowRequestPermissionRationale(this,"")
    }

    companion object {
        protected const val REQUEST_PERMISSION_PHONE = 0
    }
}