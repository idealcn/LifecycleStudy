package com.idealcn.lifecycle.study.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.idealcn.lifecycle.study.bean.AppUser
import com.idealcn.lifecycle.study.bean.ResponseLoginBean

/**
 * @author: guoning
 * @date: 2018/11/30 14:17
 * @description:
 */
@Database(version = 1,entities = [ResponseLoginBean::class],exportSchema = true)
abstract class AppRoomDatabase : RoomDatabase(){

  abstract  fun useAppDao() : AppDao

}