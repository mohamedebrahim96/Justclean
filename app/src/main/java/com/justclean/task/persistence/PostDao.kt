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
import com.justclean.task.model.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostList(postList: List<Post>)

    @Query("SELECT * FROM Post")
    suspend fun getAllPostList(): List<Post>

    @Query("SELECT * FROM Post WHERE liked=1")
    suspend fun getAllFavPostList(): List<Post>

    @Query("UPDATE Post SET liked = :liked_ WHERE id = :postID_")
    suspend fun updateLikedPost(postID_: Int, liked_: Boolean)
}
