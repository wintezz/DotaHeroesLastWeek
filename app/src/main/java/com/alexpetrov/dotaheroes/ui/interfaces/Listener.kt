package com.alexpetrov.dotaheroes.ui.interfaces

import com.alexpetrov.dotaheroes.data.HeroModel

interface Listener{
    fun onClickItem(heroModel: List<HeroModel>, position: Int)
}
