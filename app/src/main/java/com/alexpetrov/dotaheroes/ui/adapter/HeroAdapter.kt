package com.alexpetrov.dotaheroes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alexpetrov.dotaheroes.data.HeroModel
import com.alexpetrov.dotaheroes.ui.interfaces.Listener
import com.alexpetrov.dotaheroes.R
import com.alexpetrov.dotaheroes.databinding.HeroItemBinding

class HeroAdapter(
    private val listener: Listener,
    private val names: List<HeroModel>
) : RecyclerView.Adapter<HeroAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hero_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val baseURl = "https://api.opendota.com${names[position].icon}"

        holder.binding.tvName.text = names[position].localized_name
        holder.binding.ivIcon.load(baseURl)
        holder.binding.btnHero.setOnClickListener {
            listener.onClickItem(names, position)
        }
    }

    override fun getItemCount(): Int = names.size


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = HeroItemBinding.bind(itemView)
    }
}