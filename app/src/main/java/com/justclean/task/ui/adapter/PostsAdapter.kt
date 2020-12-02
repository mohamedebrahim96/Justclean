/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.adapter

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.justclean.task.R


class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val items: MutableList<Pokemon> = mutableListOf()
    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemPokemonBinding>(inflater, R.layout.item_pokemon, parent, false)
        return PostsViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
                    DetailActivity.startActivity(binding.transformationLayout, items[position])
                    onClickedAt = currentClickedAt
                }
            }
        }
    }

    fun setPokemonList(pokemonList: List<Pokemon>) {
        val previousItemSize = items.size
        items.clear()
        items.addAll(pokemonList)
        notifyItemRangeChanged(previousItemSize, pokemonList.size)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.binding.apply {
            pokemon = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class PostsViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root)
}