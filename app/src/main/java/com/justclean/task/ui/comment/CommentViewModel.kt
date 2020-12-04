/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.comment

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.justclean.task.base.LiveCoroutinesViewModel
import com.justclean.task.model.PostComment
import com.justclean.task.repository.CommentRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber


class CommentViewModel @AssistedInject constructor(
    private val commentRepository: CommentRepository,
    @Assisted private val postID: Int
) : LiveCoroutinesViewModel() {

    val postCommentLiveData: LiveData<List<PostComment?>>
    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData
    private val isLoading: ObservableBoolean = ObservableBoolean(false)

    init {
        Timber.d("init DetailViewModel")

        postCommentLiveData = launchOnViewModelScope {
            isLoading.set(true)
            commentRepository.fetchCommentList(
                id = postID,
                onSuccess = { isLoading.set(false) },
                onError = { _toastLiveData.postValue(it) }
            ).asLiveData()
        }
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(postID: Int): CommentViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            postID: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(postID) as T
            }
        }
    }
}
