package cn.mama.marketplace.utils

import java.util.Random

object MockUtil {

    // 返回一段随机的中文字符串，20~100字之间
    fun getRandomChineseString(): String {
        val length = (20..100).random()
        val sb = StringBuilder()
        for (i in 0 until length) {
            sb.append(getRandomChineseChar().toString())
        }
        return sb.toString()
    }

    // 随机获取一个中文字符
    private fun getRandomChineseChar(): Char {
        val random = Random()
        // 中文字符的 Unicode 范围是 0x4E00 到 0x9FA5
        val codePoint = 0x4E00 + random.nextInt(0x9FA5 - 0x4E00 + 1)
        return codePoint.toChar()
    }
}