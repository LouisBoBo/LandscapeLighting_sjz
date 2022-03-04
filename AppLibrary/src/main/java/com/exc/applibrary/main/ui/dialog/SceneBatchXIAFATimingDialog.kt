package com.exc.applibrary.main.ui.dialog

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.customview.WrapContentHeightViewPager
import com.exc.applibrary.main.model.BaseBean
import com.exc.applibrary.main.model.ControllerByIdSceneNameBean
import com.exc.applibrary.main.ui.fragment.ScenePerWeekSelectFragment
import com.exc.applibrary.main.ui.fragment.ScenePerTimeSelectFragment
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.DateUtil
import com.exc.applibrary.main.utils.ToastUtils
import com.tlz.fucktablayout.FuckTabLayout
import kotlinx.android.synthetic.main.dialog_scene_detail_by_id_timing.*
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.CommonUtil.showShortToast
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil
import java.lang.Exception
import kotlin.collections.ArrayList


class SceneBatchXIAFATimingDialog(private var list: ArrayList<ControllerByIdSceneNameBean.DataBean>?) : DialogFragment(), View.OnClickListener, OnHttpResponseListener {
    private lateinit var ft: FuckTabLayout
    private lateinit var mViewpager: WrapContentHeightViewPager
    private var mViewpagerPos = 0
    private lateinit var mActivity: Activity

    private lateinit var loadIngDialg: CustomDialog

    companion object {
        val XIAFA_SUCCESS = "batch_xiafa_success"
        var beginDate = ""
        var endDate = ""
        var time = ""
        var cycleTypes = arrayListOf<Int>()
        fun showDialog(myList: ArrayList<ControllerByIdSceneNameBean.DataBean>?, fragmentManager: FragmentManager) {
            val dialog = SceneBatchXIAFATimingDialog(myList)
            dialog.show(fragmentManager, "tag")
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ll_cancel -> {
                dismiss()
            }
            R.id.ll_zhixing -> {
                var type = if (mViewpagerPos == 1) 2 else 1
                if (type == 1) {
                    //结束日期不能小于开始日期
                    if (DateUtil.dateToStampOnlyDay(endDate) < DateUtil.dateToStampOnlyDay(beginDate)) {
                        showShortToast(mActivity, "结束日期不能小于开始日期")
                        return
                    }
                }
                loadIngDialg.show()
                HttpRequest.scenePIliangXiafaTiming(type, beginDate, endDate, time, cycleTypes, list, 1, this)
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_scene_detail_by_id_timing, container, false)
        cycleTypes.clear()
        mViewpager = view.findViewById(R.id.mViewpager)
        mActivity = requireActivity()
        loadIngDialg = CustomDialog(mActivity)
        ft = view.findViewById(R.id.ft)

        view.findViewById<LinearLayout>(R.id.ll_cancel).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.ll_zhixing).setOnClickListener(this)


        val pageAdapter = BuyerLiveGoodsPageAdapter(childFragmentManager)
        mViewpager.adapter = pageAdapter
        ft.setupWithViewPager(mViewpager)

        mViewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                mViewpagerPos = position
                if (position == 0) {
                    tv_zhixing.text = "时间段执行"
                    iv_zhixing.setImageResource(R.drawable.icon_timing)
                } else {
                    tv_zhixing.text = "周期执行"
                    iv_zhixing.setImageResource(R.drawable.cycle)
                }
            }
        })

        return view
    }

    override fun onStart() {
        super.onStart()
        val window: Window? = dialog!!.window
        if (window != null) {
            // 一定要设置Background，如果不设置，window属性设置无效
            window.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.activity_bg)))
            val dm = DisplayMetrics()
            if (activity != null) {
                val windowManager: WindowManager? = requireActivity().windowManager
                if (windowManager != null) {
                    windowManager.getDefaultDisplay().getMetrics(dm)
                    val params: WindowManager.LayoutParams = window.attributes
                    params.gravity = Gravity.BOTTOM
                    window.setWindowAnimations(R.style.share_animation)
                    // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT
//                    params.height = height
                    window.attributes = params
                }
            }
        }
    }


    class BuyerLiveGoodsPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        var fragments: MutableList<Fragment> = ArrayList()
        var titles: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }

        init {
            val onePriceFragment = ScenePerTimeSelectFragment()
            fragments.add(onePriceFragment)
            titles.add("时间段执行")
            val auctionFragment = ScenePerWeekSelectFragment()
            fragments.add(auctionFragment)
            titles.add("周期执行")
            notifyDataSetChanged()
        }
    }

    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        loadIngDialg.dismiss()
        dismiss()
        if (StringUtil.isEmpty(resultJson)) {
            ToastUtils.showErrorToast(mActivity, "数据异常")
            return
        }
        val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
        CommonUtils.exitLogin(baseBean.code, activity)
        if (baseBean.code != 200) {
            ToastUtils.showErrorToast(mActivity, baseBean.message)

        } else {
            showShortToast(mActivity, "下发场景成功")
            //清除已选的场景
            EventBus.getDefault().post(XIAFA_SUCCESS)
        }
    }


}

