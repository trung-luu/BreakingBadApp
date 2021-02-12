package com.trunghtluu.breakingbadapp.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trunghtluu.breakingbadapp.R
import com.trunghtluu.breakingbadapp.model.Character

class CharacterListAdapter (
    var characterList: List<Character>,
    var context: Context,
    val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_character_list_view_holder, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.textView.text = characterList[position].name
        Glide.with(context)
            .load(characterList[position].img)
            .apply(RequestOptions.centerCropTransform())
            .override(300, 300)
            .into(holder.imageView)
        holder.itemView.setOnClickListener { itemClickListener(position) }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView
        val textView: TextView

        init {
            imageView = itemView.findViewById(R.id.ivCharacterImage)
            textView = itemView.findViewById(R.id.tvCharacterName)
        }
    }

}