package com.idealcn.lifecycle.study.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.idealcn.lifecycle.study.ActivityLifecycleObserver
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.base.BaseActivity
import com.idealcn.lifecycle.study.bean.Chapter
import com.idealcn.lifecycle.study.ui.fragment.*
import com.idealcn.lifecycle.study.ui.mvp.presenter.MainPresenter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.main_activity
    }


//    private lateinit var      disposable: Disposable

    private val chapterList: ArrayList<Chapter> = arrayListOf()
//    private val fragmentList  = listOf<BaseFragment<BaseContract.BaseView>>(MainFragment<HomeView>(),KnowledgeFragment(),
//                                                    NavigationFragment(),ProgrammeFragment())

    private val compositeDisposable = CompositeDisposable()


    val activityLifecycleObserver = ActivityLifecycleObserver()


    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private  var drawerFragment : DrawerFragment? = null

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
        lifecycle.addObserver(activityLifecycleObserver)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }


        actionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawerLayout, toolBar,
            R.string.navigation, R.string.collect
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)

            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)

            }
        }


        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        homePager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
//                val fragment : BaseFragment<MainView> = fragmentList[position]
//                return fragment
                return when (position) {
                    0 -> {
                        MainFragment()
                    }
                    1 -> {
                        KnowledgeFragment()
                    }
                    2 -> {
                        NavigationFragment()
                    }
                    3 -> {
                        ProgrammeFragment()
                    }
                    else -> {
                        MainFragment()
                    }
                }
            }

            override fun getCount(): Int {
                return 4
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return chapterList[position].name
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    homePager.setCurrentItem(0, true)
                }

                R.id.action_knowledge_system -> {
                    homePager.setCurrentItem(1, true)
                }

                R.id.action_navigation -> {
                    homePager.setCurrentItem(2, true)

                }


                R.id.action_project -> {
                    homePager.setCurrentItem(3, true)

                }
                else -> {
                    homePager.setCurrentItem(0, true)
                }
            }
            true
        }



        if (supportFragmentManager.findFragmentByTag("drawer")==null){
            supportFragmentManager.beginTransaction().add(R.id.drawContainer,DrawerFragment(),"drawer").commit()
        }else {
            drawerFragment = supportFragmentManager.findFragmentByTag("drawer") as DrawerFragment
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
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

    /**
     * 关闭左侧
     */
    fun closeDrawer() {
        drawerLayout.closeDrawer(Gravity.LEFT)
    }

}
