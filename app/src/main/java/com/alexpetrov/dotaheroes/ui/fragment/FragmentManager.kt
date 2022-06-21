package com.alexpetrov.dotaheroes.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alexpetrov.dotaheroes.R

object FragmentManager {

    var currentFrag: Fragment? = null

    fun setFragment(newFragment: Fragment, activity: AppCompatActivity) {
        val transaction = activity
            .supportFragmentManager.beginTransaction()
        transaction.replace(R.id.placeHolder, newFragment)
        transaction.commit()
        currentFrag = newFragment
    }
}