package com.idealcn.lifecycle.study.bean

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * author:guoning
 * date: 2018/11/29 23:23
 * 描述:
 */
@Entity(tableName = "loginbean")
 data class ResponseLoginBean(@ColumnInfo(name = "username")var username : String,
                              @ColumnInfo(name = "type")var type : Int,
                              @ColumnInfo(name = "token")var token :String,
                              @ColumnInfo(name = "password")var password :String,
                              @PrimaryKey @ColumnInfo(name = "id")var id :Long,
                              @ColumnInfo(name = "icon")var icon : String,
                              @ColumnInfo(name = "email")var email :String
) {

    /*
       "chapterTops": [],
        "collectIds": [],
        "email": "",
        "icon": "",
        "id": 13644,
        "password": "",
        "token": "",
        "type": 0,
        "username": "ideal0424"
     */
}