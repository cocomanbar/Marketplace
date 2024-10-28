package cn.mama.marketplace.ui.home.commend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.FragmentCommendBinding
import cn.mama.marketplace.ui.common.ui.BaseFragment


class CommendFragment : BaseFragment() {

    private lateinit var binding: FragmentCommendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommendBinding.inflate(inflater, container, false)
        return onCreateViewLayoutIfNeeded(binding.root)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CommendFragment()
    }
}