package com.justclean.task.network

import com.justclean.task.MainCoroutinesRule
import com.nhaarman.mockitokotlin2.mock
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

/**
 * Created by @mohamedebrahim96 on 12/5/20.
 */
@ExperimentalCoroutinesApi
class PostServiceTest : ApiAbstract<PostService>() {

    private lateinit var service: PostService
    private val client: PostClient = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createService(PostService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchPokemonListFromNetworkTest() = runBlocking {
        //enqueueResponse("/PokemonResponse.json")
        val response = service.fetchPostList()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)
        mockWebServer.takeRequest()

        client.fetchPostList()
        MatcherAssert.assertThat(responseBody[0].id, CoreMatchers.`is`(10))
        MatcherAssert.assertThat(responseBody[0].title, CoreMatchers.`is`("Post title test"))
        MatcherAssert.assertThat(responseBody[0].body, CoreMatchers.`is`("Post body test"))
    }

    @Throws(IOException::class)
    @Test
    fun fetchPokemonInfoFromNetworkTest() = runBlocking {
        //enqueueResponse("/Bulbasaur.json")
        val response = service.fetchPostComment(10)
        val responseBody = requireNotNull((response as ApiResponse.Success).data)
        mockWebServer.takeRequest()
        client.fetchPostComment(id = 10)
        MatcherAssert.assertThat(responseBody[0].id, CoreMatchers.`is`(10))
        MatcherAssert.assertThat(responseBody[0].name, CoreMatchers.`is`("name comment test"))
        MatcherAssert.assertThat(responseBody[0].body, CoreMatchers.`is`("email comment test"))
        MatcherAssert.assertThat(responseBody[0].email, CoreMatchers.`is`("body comment test"))
    }
}