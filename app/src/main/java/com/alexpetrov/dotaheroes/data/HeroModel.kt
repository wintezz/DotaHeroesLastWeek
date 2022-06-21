package com.alexpetrov.dotaheroes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroModel(
    val id: Int,
    val name: String,
    val localized_name: String,
    val primary_attr: String,
    val img: String,
    val icon: String,
    val base_health: Int,
    val base_mana: Int,
    val base_str: Int,
    val base_agi: Int,
    val base_int: Int,
    val move_speed: Int,
    val hero_id: Int
)
