package com.justclean.task.utils

import com.justclean.task.model.Post
import com.justclean.task.model.PostComment

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
object MockUtil {


    fun mockPost() = Post(
        liked = false,
        userId = 10,
        id = 20,
        title = "Post title test",
        body = "Post body test",
    )

    fun mockPostList() = listOf(mockPost())


    fun mockPostComment() = PostComment(
        postId = 10,
        id = 200,
        name = "",
        email = "",
        body = ""
    )

    fun mockPostCommentList() = listOf(mockPostComment())

}