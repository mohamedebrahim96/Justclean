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

    @Query("SELECT * FROM Post WHERE page = :page_")
    suspend fun getPostList(page_: Int): List<Post>

    @Query("SELECT * FROM Post")
    suspend fun getAllPostList(): List<Post>
}
