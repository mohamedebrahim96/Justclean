/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.favourite

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.justclean.task.base.LiveCoroutinesViewModel
import com.justclean.task.model.Post
import com.justclean.task.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber


class FavouriteViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @androidx.hilt.Assisted
    private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

    private val postFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val postListLiveData: LiveData<List<Post>>

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    init {
        Timber.d("init MainViewModel")
        postListLiveData = postFetchingIndex.asLiveData().switchMap {
            isLoading.set(true)
            launchOnViewModelScope {
                this.mainRepository.getFavPostList(
                    onSuccess = { isLoading.set(false) },
                    onError = { _toastLiveData.postValue(it) }
                ).asLiveData()
            }
        }
    }

    @MainThread
    suspend fun savePost(postID: Int, liked: Boolean) =
        this.mainRepository.savePostLocally(postID, liked)

}