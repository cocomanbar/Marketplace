package cn.mama.marketplace.ui.home.commend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.mama.marketplace.databinding.FragmentCommendBinding
import cn.mama.marketplace.network.ResultJson
import cn.mama.marketplace.ui.common.ui.BaseFragment
import cn.mama.marketplace.utils.JsonUtil
import com.google.gson.reflect.TypeToken
import com.hjq.toast.Toaster
import java.lang.reflect.Type

/**
 * 首页-推荐列表界面
 */
class CommendFragment : BaseFragment() {

    private lateinit var binding: FragmentCommendBinding

    private lateinit var commendList: MutableList<CommendDataItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        commendList = mutableListOf()

        // 加载本地json文件
        val type: Type = object : TypeToken<ResultJson<CommendData>>() {}.type
        val model: ResultJson<CommendData> =
            JsonUtil.loadJsonAsResultJson("commendListJson.json", type = type)
        model.data?.list.let {
            if (it != null) {
                commendList.addAll(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommendBinding.inflate(inflater, container, false)
        return onCreateViewLayoutIfNeeded(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CommendAdapter(requireContext(), commendList.toTypedArray())
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val item = adapter.getItem(position)
            if (item !is CommendDataItem || item.activity.isNullOrEmpty()) {
                return@setOnItemClickListener
            }
            Toaster.show("点击了第${position + 1}个：${item.title}")
            // 根据字符串反转成class
            try {
                val clazz = Class.forName(item.activity)
                startActivity(Intent(requireContext(), clazz))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CommendFragment()
    }
}