package cn.mama.marketplace.learn.gson

import android.os.Bundle
import cn.mama.marketplace.databinding.ActivityLearnGsonBinding
import cn.mama.marketplace.network.ResultJson
import cn.mama.marketplace.ui.common.ui.BaseActivity
import cn.mama.marketplace.ui.home.commend.CommendData
import cn.mama.marketplace.utils.JsonUtil
import com.google.gson.reflect.TypeToken
import com.hjq.gson.factory.GsonFactory
import java.lang.reflect.Type

class LearnGsonActivity : BaseActivity() {

    private lateinit var binding: ActivityLearnGsonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnGsonBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

        binding.tabBar.leftView.setOnClickListener {
            onBack()
        }

        binding.getJson.setOnClickListener {
            val jsonStr = JsonUtil.loadJson("commendListJson.json")
            binding.textView.text = jsonStr
        }
        binding.jsonToMap.setOnClickListener {
            val jsonStr = JsonUtil.loadJson("commendListJson.json")
            val jsonMap = JsonUtil.jsonToMap(jsonStr)
            binding.textView.text = jsonMap.toString()
        }
        binding.jsonToModel.setOnClickListener {
            val type: Type = object : TypeToken<ResultJson<CommendData>>() {}.type
            val resultJson =
                JsonUtil.loadJsonAsResultJson<CommendData>("commendListJson.json", type)
            binding.textView.text = resultJson.toString()
        }
        binding.mapToModel.setOnClickListener {
            val gson = GsonFactory.getSingletonGson()
            val type: Type = object : TypeToken<ResultJson<CommendData>>() {}.type
            val resultJson =
                JsonUtil.loadJsonAsResultJson<CommendData>("commendListJson.json", type)
            val json = gson.toJson(resultJson)
            binding.textView.text = json
        }
    }
}