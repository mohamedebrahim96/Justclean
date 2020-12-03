/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.main

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.justclean.task.base.LiveCoroutinesViewModel
import com.justclean.task.model.Post
import com.justclean.task.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber


class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val pokemonListLiveData: LiveData<List<Post>>

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    init {
        Timber.d("init MainViewModel")

        pokemonListLiveData = pokemonFetchingIndex.asLiveData().switchMap {
            isLoading.set(true)
            launchOnViewModelScope {
                this.mainRepository.fetchPostList(
                    page = it,
                    onSuccess = { isLoading.set(false) },
                    onError = { _toastLiveData.postValue(it) }
                ).asLiveData()
            }
        }
    }

    @MainThread
    fun fetchPokemonList() {
        pokemonFetchingIndex.value++
    }
}
