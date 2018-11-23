# LifecycleStudy
# RxLife


# ViewModel
 ``` kotlin
     //在同一个Fragment或者Activity中,多次获取的ViewModel都是同一个对象
     val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
```
