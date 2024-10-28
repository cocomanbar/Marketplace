package cn.mama.marketplace.ui.home.discovery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.FragmentDiscoveryBinding
import cn.mama.marketplace.ui.common.ui.BaseFragment

class DiscoveryFragment : BaseFragment() {

    private lateinit var binding: FragmentDiscoveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoveryBinding.inflate(layoutInflater, container, false)
        return onCreateViewLayoutIfNeeded(binding.root)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DiscoveryFragment()
    }
}