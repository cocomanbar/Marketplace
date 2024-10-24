package cn.mama.marketplace.ui.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cn.mama.marketplace.R

class TabItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val textView: TextView

    private var normalText: String? = null
    private var highText: String? = null
    private var normalSrc: Drawable? = null
    private var highSrc: Drawable? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_tab_navigation_item, this, true)
        imageView = findViewById<ImageView>(R.id.image)
        textView = findViewById<TextView>(R.id.text)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TabItemView, 0, 0)
            normalText = typedArray.getString(R.styleable.TabItemView_normal_text)
            highText = typedArray.getString(R.styleable.TabItemView_high_text)
            normalSrc = typedArray.getDrawable(R.styleable.TabItemView_normal_src)
            highSrc = typedArray.getDrawable(R.styleable.TabItemView_high_src)
            isSelected = typedArray.getBoolean(R.styleable.TabItemView_isSelected, false)
            layoutIfNeeded()
            typedArray.recycle()
        }
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)

        layoutIfNeeded()
    }

    private fun layoutIfNeeded() {
        if (isSelected) {
            textView.text = highText
            imageView.setImageDrawable(highSrc)
        } else {
            textView.text = normalText
            imageView.setImageDrawable(normalSrc)
        }
        textView.isSelected = isSelected
    }
}