package com.trunghtluu.breakingbadapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trunghtluu.breakingbadapp.R
import com.trunghtluu.breakingbadapp.ui.list.CharacterListViewModel

class CharacterDetailFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val viewModel = ViewModelProvider(requireActivity()).get(CharacterListViewModel::class.java)
        val characterImage = requireActivity().findViewById<ImageView>(R.id.ivImage)
        val characterName = requireActivity().findViewById<TextView>(R.id.tvNameValue)
        val characterOccupation = requireActivity().findViewById<TextView>(R.id.tvOccupationValue)
        val characterStatus = requireActivity().findViewById<TextView>(R.id.tvStatusValue)
        val characterNickname = requireActivity().findViewById<TextView>(R.id.tvNickNameValue)
        val characterAppearance = requireActivity().findViewById<TextView>(R.id.tvAppearanceValue)

        val character = viewModel.selectedCharacter

        Glide.with(requireContext())
            .load(character.img)
            .apply(RequestOptions.centerCropTransform())
            .override(500, 500)
            .into(characterImage)
        characterName.text = character.name
        characterOccupation.text = character.occupation?.joinToString(", ")
        characterStatus.text = character.status
        characterNickname.text = character.nickname
        characterAppearance.text = character.appearance?.joinToString(", ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}