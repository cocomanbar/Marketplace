package cn.mama.marketplace.learn.baseRecyclerView

import android.os.Bundle
import android.widget.TextView
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.ActivityLearnRecyclerViewBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity
import cn.mama.marketplace.utils.MockUtil
import cn.mama.marketplace.utils.logD
import com.drake.brv.BindingAdapter
import com.drake.brv.item.ItemBind
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup

class LearnRecyclerViewActivity : BaseActivity() {

    private lateinit var binding: ActivityLearnRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

        // 刷新
        binding.refreshLayout.setOnRefreshListener {
            // 异步任务
            Thread {
                Thread.sleep(1000)
                runOnUiThread {
                    binding.refreshLayout.finishRefresh()
                    logD(tag, "结束刷新头部")
                }
            }.start()
        }
        binding.refreshLayout.setOnLoadMoreListener {
            // 异步任务
            Thread {
                Thread.sleep(2000)
                runOnUiThread {
                    binding.refreshLayout.finishLoadMore()
                    logD(tag, "结束刷新尾部")
                }
            }.start()
        }

        // 1对1
        // singleDataTest()

        // 多对多
        // 一对多
        //multiDataTest()

        singleDataTest2()
    }

    private fun singleDataTest2() {

        // 数据模型
        data class Data(val title: String) : ItemBind {

            // 数据绑定，但是不能在这里设置点击事件
            override fun onBind(vh: BindingAdapter.BindingViewHolder) {
                vh.findView<TextView>(R.id.tv_title).text = title
            }
        }

        val chineseList: MutableList<Any> = mutableListOf()
        for (i in 0..20) {
            chineseList.add(Data(MockUtil.getRandomChineseString()))
        }

        binding.recyclerView.linear().setup {
            addType<Data>(R.layout.layout_number_short_item)
        }.models = chineseList
    }

    // 多格式数据
    private fun multiDataTest() {

        class DataLine1(
            val title: String,
        )

        class DataLine2(
            val title: String,
        )

        val chineseList: MutableList<Any> = mutableListOf()
        for (i in 0..20) {
            if (i % 2 == 0) {
                chineseList.add(DataLine1(MockUtil.getRandomChineseString()))
            } else {
                var title = MockUtil.getRandomChineseString()
                title += if (title.length >= 50) {
                    "Long"
                } else {
                    "Short"
                }
                chineseList.add(DataLine2(title))
            }
        }

        binding.recyclerView.linear().setup {
            addType<DataLine1>(R.layout.layout_number_short_item)
            addType<DataLine2> {
                when (title.length > 50) {
                    true -> R.layout.layout_number_long_2_item
                    false -> R.layout.layout_number_long_item
                }
            }
            onBind {
                when (itemViewType) {
                    R.layout.layout_number_short_item -> {
                        findView<TextView>(R.id.tv_title).text = getModel<DataLine1>().title
                    }

                    R.layout.layout_number_long_item -> {
                        findView<TextView>(R.id.tv_title).text = getModel<DataLine2>().title
                    }

                    R.layout.layout_number_long_2_item -> {
                        findView<TextView>(R.id.tv_title).text = getModel<DataLine2>().title
                    }
                }
            }
            onClick(R.id.tv_title) {
                when (itemViewType) {
                    R.layout.layout_number_short_item -> {
                        logD(tag.toString(), "点击了：${getModel<DataLine1>().title}")
                    }

                    R.layout.layout_number_long_item -> {
                        logD(tag.toString(), "点击了：${getModel<DataLine2>().title}")
                    }

                    R.layout.layout_number_long_2_item -> {
                        logD(tag.toString(), "点击了：${getModel<DataLine2>().title}")
                    }
                }
            }
        }.models = chineseList
    }

    // 单格式数据
    private fun singleDataTest() {
        val chineseList: MutableList<String> = mutableListOf()
        for (i in 0..20) {
            chineseList.add(MockUtil.getRandomChineseString())
        }
        binding.recyclerView.linear().setup {
            addType<String>(R.layout.layout_number_short_item)
            onBind {
                findView<TextView>(R.id.tv_title).text = getModel<String>()
            }
            onClick(R.id.tv_title) {
                logD(tag.toString(), "点击了：${getModel<String>()}")
            }
        }.models = chineseList
    }
}