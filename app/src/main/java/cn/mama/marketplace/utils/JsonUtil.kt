package cn.mama.marketplace.utils

import cn.mama.marketplace.App
import cn.mama.marketplace.network.ResultJson
import com.google.gson.reflect.TypeToken
import com.hjq.gson.factory.GsonFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type


object JsonUtil {

    /**
     * 加载本地json
     *
     * @param fileName 文件名：fileName.json
     * @return json字符串
     */
    fun loadJson(fileName: String): String {
        val stringBuilder = StringBuilder()
        try {
            val `is` = App.context.assets.open("jsons/$fileName")
            val inputStreamReader = InputStreamReader(`is`)
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

    /**
     * 将json字符串转为map对象
     *
     * @param jsonStr json字符串
     * @return map对象
     */
    fun jsonToMap(jsonStr: String): Map<String, Any> {
        val gson = GsonFactory.getSingletonGson()
        val mapType: Type = object : TypeToken<Map<String?, Any?>?>() {}.type
        val map = gson.fromJson(jsonStr, mapType) as Map<String, Any>
        return map
    }

    /**
     * 将json字符串转为list对象
     *
     * @param jsonStr json字符串
     * @return list对象
     */
    fun jsonToList(jsonStr: String): List<Any> {
        val gson = GsonFactory.getSingletonGson()
        val listType: Type = object : TypeToken<List<Any?>?>() {}.type
        val list = gson.fromJson(jsonStr, listType) as List<Any>
        return list
    }

    /**
     * 加载本地的json文件为泛型网络对象
     *
     * @param fileName 文件名：fileName.json
     * @param type     泛型类型Type
     * @return ResultJson<T>
     */
    fun <T> loadJsonAsResultJson(fileName: String, type: Type): ResultJson<T> {
        val json = loadJson(fileName)
        val gson = GsonFactory.getSingletonGson()
        // 使用 Gson 解析 JSON 字符串
        val resultJson: ResultJson<T> = gson.fromJson(json, type)
        return resultJson
    }
}