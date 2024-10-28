package cn.mama.marketplace.ui.home.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.mama.marketplace.databinding.FragmentDailyBinding
import cn.mama.marketplace.ui.common.ui.BaseFragment


class DailyFragment : BaseFragment() {

    private lateinit var binding: FragmentDailyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(layoutInflater, container, false)
        return onCreateViewLayoutIfNeeded(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DailyFragment()
    }
}