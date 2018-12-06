package com.idealcn.lifecycle.study.dagger.component

import com.idealcn.lifecycle.study.ui.activity.TencentChapterActivity
import dagger.Component

/**
 * @author: guoning
 * @date: 2018/12/6 16:16
 * @description:
 */
@Component
interface ChapterComponent {
    fun inject(activity: TencentChapterActivity)
}