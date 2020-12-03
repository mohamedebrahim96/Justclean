/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.repository

import androidx.annotation.WorkerThread
import com.justclean.task.model.PostComment
import com.justclean.task.network.PostClient
import com.justclean.task.persistence.PostCommentDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val postClient: PostClient,
    private val postCommentDao: PostCommentDao
) : Repository {

    @WorkerThread
    suspend fun fetchCommentList(
        id: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow<PostComment?> {
        val postComment = postCommentDao.getPostComment(id)
        if (postComment == null) {
            val response = postClient.fetchPostComment(id = id)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    postCommentDao.insertPostComment(response)
                    emit(response[0])
                    onSuccess()
                }
            }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
                .onError {
                    onError(message())
                }
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
                .onException {
                    onError(message())
                }
        } else {
            emit(postComment)
            onSuccess()
        }
    }.flowOn(Dispatchers.IO)
}
