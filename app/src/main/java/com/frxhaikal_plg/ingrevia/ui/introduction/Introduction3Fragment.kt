package com.frxhaikal_plg.ingrevia.ui.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frxhaikal_plg.ingrevia.R
import com.google.android.material.button.MaterialButton

class Introduction3Fragment : Fragment() {
    private var onNextClick: (() -> Unit)? = null
    private var onSkipClick: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_introduction3, container, false)
        
        view.findViewById<MaterialButton>(R.id.next_button).apply {
            text = getString(R.string.get_started)
            setOnClickListener {
                onNextClick?.invoke()
            }
        }
        
        view.findViewById<MaterialButton>(R.id.skip_button).setOnClickListener {
            onSkipClick?.invoke()
        }
        
        return view
    }

    fun setOnNextClickListener(listener: () -> Unit) {
        onNextClick = listener
    }

    fun setOnSkipClickListener(listener: () -> Unit) {
        onSkipClick = listener
    }
}
