package com.justclean.task.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import com.justclean.task.MainCoroutinesRule
import com.justclean.task.model.Post
import com.justclean.task.network.PostClient
import com.justclean.task.network.PostService
import com.justclean.task.persistence.PostDao
import com.justclean.task.repository.MainRepository
import com.justclean.task.ui.main.MainViewModel
import com.justclean.task.utils.MockUtil
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by @mohamedebrahim96 on 12/5/20.
 */

@ExperimentalCoroutinesApi
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private lateinit var mainRepository: MainRepository
    private val postService: PostService = mock()
    private val postClient: PostClient = PostClient(postService)
    private val postDao: PostDao = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        mainRepository = MainRepository(postClient, postDao)
        viewModel = MainViewModel(mainRepository, SavedStateHandle())
    }

    @Test
    fun fetchPokemonListTest() = runBlocking {
        val mockData = MockUtil.mockPostList()
        whenever(postDao.getAllPostList()).thenReturn(mockData)
        val observer: Observer<List<Post>> = mock()
        val fetchedData: LiveData<List<Post>> =
            mainRepository.fetchPostList(
                onSuccess = {},
                onError = {}
            ).asLiveData()
        fetchedData.observeForever(observer)
        delay(500L)
        verify(postDao, atLeastOnce()).getAllPostList()
        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }

}