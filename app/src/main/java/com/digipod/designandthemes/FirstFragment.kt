package com.digipod.designandthemes

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.DrawableRes
import androidx.navigation.fragment.findNavController
import com.digipod.designandthemes.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    lateinit var prefs: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireActivity().getSharedPreferences("mypref", MODE_PRIVATE)
        // Get the value of the key "theme" from the shared preferences
        val isDarkTheme = prefs.getBoolean(PREF_DARK_THEME, false)
        if (isDarkTheme) {
            binding.btnToggleTheme.text = "Toggle Light"
        } else {
            binding.btnToggleTheme.text = "Toggle Dark"
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btnToggleTheme.setOnClickListener {
            val editor = prefs.edit()           // create an editor object
            editor.putBoolean(PREF_DARK_THEME, !isDarkTheme)  // save the opposite value
            editor.apply()
            requireActivity().recreate() // recreate activity to apply theme change
        }
        binding.button2.setOnClickListener {
            val slideDown = AnimationUtils.loadAnimation(activity, R.anim.slide_down)
            it.startAnimation(slideDown)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PREF_DARK_THEME = "dark_theme"
    }
}