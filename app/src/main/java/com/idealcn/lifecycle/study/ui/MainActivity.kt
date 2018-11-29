package com.idealcn.lifecycle.study.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.idealcn.lifecycle.study.ActivityLifecycleObserver
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.Chapter
import com.idealcn.lifecycle.study.ui.fragment.KnowledgeFragment
import com.idealcn.lifecycle.study.ui.fragment.MainFragment
import com.idealcn.lifecycle.study.ui.fragment.NavigationFragment
import com.idealcn.lifecycle.study.ui.fragment.ProgrammeFragment
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {


//    private lateinit var      disposable: Disposable

    private val chapterList : ArrayList<Chapter> = arrayListOf()
    private val fragmentList  = listOf<Fragment>(MainFragment(),KnowledgeFragment(),
                                                    NavigationFragment(),ProgrammeFragment())

    private val compositeDisposable = CompositeDisposable()


    val activityLifecycleObserver = ActivityLifecycleObserver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        lifecycle.addObserver(activityLifecycleObserver)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }


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


    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }





    override fun onDestroy() {
        super.onDestroy()
        //移除生命周期观察者
        lifecycle.removeObserver(activityLifecycleObserver)
//        if (!disposable.isDisposed) {
//            disposable.dispose()
//        }

    }

}
