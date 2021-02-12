package com.trunghtluu.breakingbadapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trunghtluu.breakingbadapp.MainActivity
import com.trunghtluu.breakingbadapp.R
import com.trunghtluu.breakingbadapp.model.Character
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharacterListFragment: Fragment() {

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var observer: Observer<List<Character>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        setUpObserver()
        GlobalScope.launch {
            viewModel.getCharacters()
        }
    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        setUpOnClickListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private fun setUpViewModel() {
        val viewModelFactory = CharacterListViewModel.CharacterListViewModelFactory()
        viewModel = ViewModelProvider(context as AppCompatActivity, viewModelFactory)
            .get(CharacterListViewModel::class.java)
    }

    private fun setUpObserver() {
        observer = object : Observer<List<Character>> {
            override fun onChanged(list: List<Character>) {
                viewModel.characterList = list
                setUpRecyclerView()
            }
        }
        viewModel.getCharacterListLiveData().observe(requireActivity(), observer)
    }

    private fun setUpRecyclerView() {
        val list = viewModel.characterList
        val adapter = CharacterListAdapter(list, requireContext(), ::itemOnClickListener)
        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        recyclerView.adapter = adapter
    }

    private fun setUpOnClickListener() {
        val searchButton = requireActivity().findViewById<Button>(R.id.btnSearch)
        val searchEditText = requireActivity().findViewById<EditText>(R.id.etName)
        searchButton.setOnClickListener {
            if (searchEditText.text.isNotBlank() && searchEditText.text.isNotEmpty()) {
                viewModel.characterList = viewModel.getCharacterListLiveData().value?.filter {
                    it.name?.contains(searchEditText.text) ?: false
                } ?: listOf()
                setUpRecyclerView()
            }
        }

        val filterButton = requireActivity().findViewById<Button>(R.id.btnFilter)
        val filterEditText = requireActivity().findViewById<EditText>(R.id.etSeason)
        filterButton.setOnClickListener {
            if (filterEditText.text.isNotBlank() && filterEditText.text.isNotEmpty()) {
                viewModel.characterList = viewModel.getCharacterListLiveData().value?.filter {
                    it.appearance?.contains(filterEditText.text.toString().toInt()) ?: false
                } ?: listOf()
                setUpRecyclerView()
            }
        }

        val clearButton = requireActivity().findViewById<Button>(R.id.btnClear)
        clearButton.setOnClickListener {
            searchEditText.text.clear()
            filterEditText.text.clear()
            viewModel.characterList = viewModel.getCharacterListLiveData().value ?: listOf()
            setUpRecyclerView()
        }
    }

    private fun itemOnClickListener(position: Int) {
        viewModel.selectedCharacter = viewModel.characterList.get(position)
        (activity as MainActivity).replaceFragment()
    }
}