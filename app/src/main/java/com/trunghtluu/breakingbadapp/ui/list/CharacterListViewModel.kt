package com.trunghtluu.breakingbadapp.ui.list

import android.util.Log
import androidx.lifecycle.*
import com.trunghtluu.breakingbadapp.model.Character
import com.trunghtluu.breakingbadapp.network.RetrofitService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListViewModel: ViewModel() {
    private val characterListLiveData = MutableLiveData<List<Character>>()
    fun getCharacterListLiveData(): LiveData<List<Character>> = characterListLiveData

    var characterList: List<Character> = listOf()

    private val retrofitService = RetrofitService()

    var selectedCharacter: Character = Character()

    suspend fun getCharacters() = coroutineScope {
        launch {
            retrofitService.getCharacters().enqueue(object: Callback<List<Character>> {
                override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                    characterListLiveData.setValue(response.body())
                }

                override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                    Log.d("TAB_X", "Fail")
                }
            })
        }
    }

    class CharacterListViewModelFactory() : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass != CharacterListViewModel::class.java) {
                throw RuntimeException("Incorrect use of CharacterListViewModel.Factory, likely cause: Static import of wrong Factory class")
            }
            @Suppress("UNCHECKED_CAST")
            return CharacterListViewModel() as T
        }
    }

}