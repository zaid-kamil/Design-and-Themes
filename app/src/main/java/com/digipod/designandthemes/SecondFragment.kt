package com.digipod.designandthemes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.navigation.fragment.findNavController
import com.digipod.designandthemes.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.textView2.animate()
            .translationYBy(1000f)
            .rotationYBy(360f)
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(3000L)
            .setInterpolator(BounceInterpolator())
            .start()
        binding.textView2.setOnLongClickListener {
            binding.textView2.animate()
                .translationYBy(-1000f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(3000L)
                .setInterpolator(OvershootInterpolator())
                .setUpdateListener {
                    binding.buttonSecond.animate()
                        .translationYBy(-2000f)
                }
                .start()
            true
        }
        binding.buttonSecond.translationY = 2000f
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}