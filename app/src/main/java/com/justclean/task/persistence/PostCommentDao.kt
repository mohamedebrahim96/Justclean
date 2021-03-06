/**
 * Created by @mohamedebrahim96 on 03,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justclean.task.model.PostComment

@Dao
interface PostCommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostComment(postComment: List<PostComment>)

    @Query("SELECT * FROM PostComment WHERE postId = :postId_")
    suspend fun getAllPostComments(postId_: Int): List<PostComment?>
}
