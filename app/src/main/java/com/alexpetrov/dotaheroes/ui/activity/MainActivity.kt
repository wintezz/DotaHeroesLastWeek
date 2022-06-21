package com.alexpetrov.dotaheroes.ui.activity

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alexpetrov.dotaheroes.R
import com.alexpetrov.dotaheroes.data.HeroModel
import com.alexpetrov.dotaheroes.databinding.ActivityMainBinding
import com.alexpetrov.dotaheroes.ui.fragment.FragmentHero
import com.alexpetrov.dotaheroes.ui.fragment.FragmentManager
import com.alexpetrov.dotaheroes.ui.fragment.FragmentProgram
import com.alexpetrov.dotaheroes.ui.interfaces.Listener

class MainActivity : AppCompatActivity(), Listener {

    private lateinit var binding: ActivityMainBinding
    private var currentMenu = R.id.hero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == -1)
            || (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == -1)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                0
            )
        }
        setBottomNavigationListener()
    }

    private fun setBottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.hero -> {
                    currentMenu = R.id.hero
                    title = "DotaHeroes"
                    FragmentManager
                        .setFragment(FragmentHero.newInstance(), this)
                }
                R.id.program -> {
                    currentMenu = R.id.program
                    title = "About Program"
                    FragmentManager
                        .setFragment(FragmentProgram.newInstance(), this)
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavigation
            .selectedItemId = currentMenu
    }

    override fun onClickItem(heroModel: List<HeroModel>, position: Int) {
        Log.d("MY_LOG", "Name: $position")
    }

    companion object {
        var heroInfo = listOf<HeroModel>()
    }
}