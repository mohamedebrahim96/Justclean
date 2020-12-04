/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.repository

import androidx.annotation.WorkerThread
import com.justclean.task.model.Post
import com.justclean.task.network.PostClient
import com.justclean.task.persistence.PostDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val postClient: PostClient,
    private val PostDao: PostDao
) : Repository {

    @WorkerThread
    suspend fun fetchPostList(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        var posts = PostDao.getAllPostList()
        if (posts.isEmpty()) {
            val response = postClient.fetchPostList()
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    posts = response
                    PostDao.insertPostList(posts)
                    emit(PostDao.getAllPostList())
                    onSuccess()
                }
            }
                // handle the case when the API request gets an error response.
                //internal server error.
                .onError {
                    onError(message())
                }
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
                .onException {
                    onError(message())
                }
        } else {
            emit(PostDao.getAllPostList())
            onSuccess()
        }
    }.flowOn(Dispatchers.IO)


    @WorkerThread
    suspend fun getFavPostList(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        //var posts = PostDao.getAllFavPostList()
//        if (posts.isEmpty()) {
//            val response = postClient.fetchPostList()
//            response.suspendOnSuccess {
//                data.whatIfNotNull { response ->
//                    posts = response
//                    PostDao.insertPostList(posts)
//                    emit(PostDao.getAllPostList())
//                    onSuccess()
//                }
//            }
//                // handle the case when the API request gets an error response.
//                //internal server error.
//                .onError {
//                    onError(message())
//                }
//                // handle the case when the API request gets an exception response.
//                // e.g. network connection error.
//                .onException {
//                    onError(message())
//                }
//        } else {
            emit(PostDao.getAllFavPostList())
            onSuccess()

    }.flowOn(Dispatchers.IO)

    @WorkerThread
    suspend fun savePostLocally(postID: Int, liked: Boolean) {
        PostDao.updateLikedPost(postID, liked)
    }
}
