package com.idealcn.lifecycle.study.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.idealcn.lifecycle.study.bean.ResponseLoginBean

/**
 * @author: guoning
 * @date: 2018/11/30 14:31
 * @description:
 */
@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAppUer(data: ResponseLoginBean)

}