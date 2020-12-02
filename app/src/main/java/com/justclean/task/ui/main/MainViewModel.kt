/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.justclean.task.base.LiveCoroutinesViewModel
import com.justclean.task.model.Post
import com.justclean.task.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow


class MainViewModel /*@ViewModelInject*/ constructor(
    private val mainRepository: MainRepository,
    /*@Assisted*/ private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val postListLiveData: LiveData<List<Post>>

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)

}