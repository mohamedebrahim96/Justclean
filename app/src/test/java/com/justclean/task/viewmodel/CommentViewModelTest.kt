package com.justclean.task.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.justclean.task.MainCoroutinesRule
import com.justclean.task.model.PostComment
import com.justclean.task.network.PostClient
import com.justclean.task.network.PostService
import com.justclean.task.persistence.PostCommentDao
import com.justclean.task.repository.CommentRepository
import com.justclean.task.ui.comment.CommentViewModel
import com.justclean.task.utils.MockUtil
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by @mohamedebrahim96 on 12/5/20.
 */
@ExperimentalCoroutinesApi
class CommentViewModelTest {

    private lateinit var viewModel: CommentViewModel
    private lateinit var commentRepository: CommentRepository
    private val postService: PostService = mock()
    private val postClient: PostClient = PostClient(postService)
    private val pokemonInfoDao: PostCommentDao = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        commentRepository = CommentRepository(postClient, pokemonInfoDao)
        viewModel = CommentViewModel(commentRepository, 10)
    }

    @Test
    fun fetchPostCommentTest() = runBlocking {
        val mockData = MockUtil.mockPostCommentList()
        whenever(pokemonInfoDao.getAllPostComments(postId_ = 10)).thenReturn(mockData)

        val observer: Observer<List<PostComment?>> = mock()
        val fetchedData: LiveData<List<PostComment?>> =
            commentRepository.fetchCommentList(
                id = 10,
                onSuccess = {},
                onError = {}
            ).asLiveData()
        fetchedData.observeForever(observer)

        verify(pokemonInfoDao, atLeastOnce()).getAllPostComments(postId_ = 10)
        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }
}