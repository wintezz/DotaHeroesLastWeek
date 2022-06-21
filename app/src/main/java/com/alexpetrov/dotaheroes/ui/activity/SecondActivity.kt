package com.alexpetrov.dotaheroes.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.alexpetrov.dotaheroes.ui.activity.MainActivity.Companion.heroInfo
import com.alexpetrov.dotaheroes.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var heroInfo = MainActivity.heroInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        refreshActivity()
    }

    private fun refreshActivity() = with(binding) {
        val count = intent.getIntExtra(KEY_ID, 0)
        val baseURl = "https://api.opendota.com${heroInfo[count].img}"

        ivImage.load(baseURl)
        tvName.text = heroInfo[count].localized_name
        agility.text = heroInfo[count].base_agi.toString()
        strength.text = heroInfo[count].base_str.toString()
        intelligence.text = heroInfo[count].base_int.toString()
        moveSpeed.text = heroInfo[count].move_speed.toString()
        basicHealth.text = heroInfo[count].base_health.toString()
        basicMana.text = heroInfo[count].base_mana.toString()
        basicChar.text = when (heroInfo[count].primary_attr) {
            "str" -> "Strength"
            "agi" -> "Agility"
            "int" -> "Intelligence"
            else -> {"It's fantastic"}
        }
    }

    companion object{
        const val KEY_ID  = "id"
    }
}