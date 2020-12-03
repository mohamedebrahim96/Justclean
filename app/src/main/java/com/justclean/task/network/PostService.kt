/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.network

import com.justclean.task.model.Post
import com.justclean.task.model.PostComment
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("posts")
    suspend fun fetchPostList(): ApiResponse<List<Post>>

    @GET("/posts/{id}/comments")
    suspend fun fetchPostComment(@Path("id") id: Int): ApiResponse<PostComment>
}
