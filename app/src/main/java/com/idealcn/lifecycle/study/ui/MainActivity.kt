package com.idealcn.lifecycle.study.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.Chapter
import com.idealcn.lifecycle.study.ui.fragment.*
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {


    private lateinit var      disposable: Disposable

    private val chapterList : ArrayList<Chapter> = arrayListOf()
    private val fragmentList  = listOf<Fragment>(MainFragment(),KnowledgeFragment(),
                                                    NavigationFragment(),ProgrammeFragment())


    private lateinit var  observer :  Observer<List<Chapter>>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.drawContainer, DrawerFragment.newInstance())
                .commitNow()
        }


        homePager.adapter = object : FragmentPagerAdapter(supportFragmentManager){
            override fun getItem(position: Int): Fragment {
                return fragmentList[position]
            }
            override fun getCount(): Int {
                return fragmentList.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return chapterList[position].name
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener (object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.action_home -> {
                        homePager.setCurrentItem(0,true)
                    }

                    R.id.action_knowledge_system -> {
                        homePager.setCurrentItem(1,true)
                    }

                    R.id.action_navigation -> {
                        homePager.setCurrentItem(2,true)

                    }


                    R.id.action_project -> {
                        homePager.setCurrentItem(3,true)

                    }
                    else -> {
                        homePager.setCurrentItem(0,true)
                    }
                }
                return true
            }

        })


//        val tabLayout = homeTabLayout
//        tabLayout.setupWithViewPager(homePager)
//
//        observer = object : Observer<List<Chapter>>{
//            override fun onComplete() {
//            }
//
//            override fun onSubscribe(d: Disposable) {
//            }
//
//            override fun onNext(list: List<Chapter>) {
//                chapterList.addAll(list)
//                list.forEach{ chapter ->
//                    val chapterFragment = ChapterFragment()
//                    val bundle = Bundle()
//                    bundle.putSerializable("chapter",chapter)
//                    chapterFragment.arguments = bundle
//                    fragmentList.add(chapterFragment)
//                    homePager.adapter?.notifyDataSetChanged()
//                }
//            }
//
//            override fun onError(e: Throwable) {
//            }
//        }
//        chapterList()
    }




//    private fun chapterList(){
//        val single = RetrofitClient.newInstance().api.chapterList()
//        disposable = single.ext()
//            .subscribe({
//                val errorCode = it.errorCode
//                if(errorCode == 0){
//                    val list  = it.data
//                    val observable = Observable.fromArray(list)
//                    //将接口返回的数据交给观察者去处理
//                    observable.subscribeWith(observer)
//                }
//            }, {
//                val message = it.message
//            })
//    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }

    }

}
