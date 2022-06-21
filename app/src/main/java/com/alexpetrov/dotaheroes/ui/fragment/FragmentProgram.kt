package com.alexpetrov.dotaheroes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexpetrov.dotaheroes.R

class FragmentProgram : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater
            .inflate(R.layout.fragment_program, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentProgram()
    }
}