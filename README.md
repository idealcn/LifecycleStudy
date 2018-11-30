# wanandroid-kotlin
# RxLife


# ViewModel
 ``` kotlin
     //在同一个Fragment或者Activity中,多次获取的ViewModel都是同一个对象
     val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
```


# git
## 分支
```
查看分支：git branch

创建分支：git branch <name>

切换分支：git checkout <name>

创建+切换分支：git checkout -b <name>

合并某分支到当前分支：git merge <name>

删除分支：git branch -d <name>
```


# Room数据库
添加依赖
```
def room_version = "2.1.0-alpha02"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version" // use kapt for Kotlin
    // optional - RxJava support for Room
    implementation "androidx.room:room-rxjava2:$room_version"
```
