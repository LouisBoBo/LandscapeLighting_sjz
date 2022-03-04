package com.exc.applibrary.main.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.exc.applibrary.R
import com.exc.applibrary.main.adapter.CommonPagerAdapter
import com.exc.applibrary.main.ui.fragment.SceneSwitchLeftFragment
import com.exc.applibrary.main.ui.fragment.SceneSwitchRightFragment
import kotlinx.android.synthetic.main.activity_scene_list.*
import zuo.biao.library.base.BaseActivity
import java.util.ArrayList

//场景切换
@RequiresApi(Build.VERSION_CODES.N)
class SceneSwitchActivity : BaseActivity() {
    private val mFragmentList: ArrayList<Fragment> = arrayListOf()
    private val mPageTitleList: ArrayList<String> = arrayListOf()
    private var mPagerAdapter: CommonPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_list)
        header_left_img.setOnClickListener { finish() }

        header_right_img.setOnClickListener {

            toActivity(Intent(this, SearchBuildingActivity::class.java)
                    .putExtra("fromScene", true
                    )


            )


        }
        initData()
    }

    override fun initData() {
        mPageTitleList.add("节点选择场景")
        mPageTitleList.add("场景选择节点")
        mFragmentList.add(SceneSwitchLeftFragment())
        mFragmentList.add(SceneSwitchRightFragment())
        mPagerAdapter = CommonPagerAdapter(this, supportFragmentManager,
                mFragmentList, mPageTitleList)
        viewpager.adapter = mPagerAdapter
        ft.setupWithViewPager(viewpager)
    }

    override fun initEvent() {
    }

    override fun initView() {
    }
}