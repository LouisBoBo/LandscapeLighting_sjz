package com.exc.applibrary.main.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.exc.applibrary.R
import com.exc.applibrary.main.adapter.CommonPagerAdapter
import com.exc.applibrary.main.model.OrderCountEvent
import com.exc.applibrary.main.other.WorKOrderTitles
import com.exc.applibrary.main.ui.fragment.MyWorkOrderListPageFragment
import kotlinx.android.synthetic.main.activity_my_work_order.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import java.util.*

class MyWorkOrderListActivity : BaseActivity(), View.OnClickListener {
    private val mFragmentList: ArrayList<Fragment> = arrayListOf()
    private val mPageTitleList: ArrayList<String> = WorKOrderTitles.allType
    private var mPagerAdapter: CommonPagerAdapter? = null
//    private var currentPos: Int? = 0


    companion object {
        @JvmStatic
        fun createIntent(mActivity: Activity): Intent {
            return Intent(mActivity, MyWorkOrderListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_work_order)
        initEventBus(true)
        initView()
        initData()
        initEvent()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(orderCountEvent: OrderCountEvent) {

//        ft.addDotBadge(0)
//        // 数字角标.
//        val numberBadge = ft.addNumberBadge(1, 1)
//        ft.addNumberBadge(2, 10)

        if(orderCountEvent.count > 0){
            ft.addNumberBadge(orderCountEvent.position, orderCountEvent.count)
        }

    }


    override fun initView() {
        header_center_text.text = "我的工单"
        header_right_img.setImageDrawable(getDrawable(R.drawable.add_new_order))
        header_right_img.visibility = View.VISIBLE
        header_right_img.setOnClickListener(this)
        header_left_img.setOnClickListener { finish() }

    }

    override fun initData() {
        repeat(mPageTitleList.size) {
            mFragmentList.add(MyWorkOrderListPageFragment.getInstance(mPageTitleList[it], it))
        }
        mPagerAdapter = CommonPagerAdapter(this, supportFragmentManager,
                mFragmentList, mPageTitleList)
        viewpager.adapter = mPagerAdapter
        ft.setupWithViewPager(viewpager)


//        ft.addDotBadge(0)
//        // 数字角标.
//        val numberBadge = ft.addNumberBadge(1, 1)
//        ft.addNumberBadge(2, 10)


//        viewpager!!.addOnPageChangeListener(object : OnPageChangeListener {
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
//            override fun onPageSelected(position: Int) {
//                currentPos = position
////                showShortToast("当前位置:" + currentPos.toString())
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {}
//        })
    }

    override fun initEvent() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.header_left_img -> {
                finish()
            }
            R.id.header_right_img -> {
                toActivity(OrderCreateNewActivity.createIntent(context))
            }
        }
    }


}