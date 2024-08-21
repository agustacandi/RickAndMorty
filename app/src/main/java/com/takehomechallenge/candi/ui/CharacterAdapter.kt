package com.takehomechallenge.candi.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.takehomechallenge.candi.databinding.ItemCharacterBinding
import com.takehomechallenge.core.domain.character.model.Character

class CharacterAdapter(private val navDirections: (Int) -> NavDirections) :
    ListAdapter<Character, CharacterAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(
        private val binding: ItemCharacterBinding,
        private val navDirections: (Int) -> NavDirections
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            with(binding) {
                characterImage.load(character.image) {
                    placeholder(ColorDrawable(Color.LTGRAY))
                }
                characterName.text = character.name
                root.setOnClickListener { view ->
                    view.findNavController().navigate(navDirections(character.id!!))
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemCharacterBinding.inflate(from(parent.context), parent, false)
        return MyViewHolder(binding, navDirections)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}