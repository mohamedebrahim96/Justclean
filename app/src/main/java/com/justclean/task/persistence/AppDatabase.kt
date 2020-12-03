/**
 * Created by @mohamedebrahim96 on 03,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justclean.task.model.Post
import com.justclean.task.model.PostComment

@Database(entities = [Post::class, PostComment::class], version = 1, exportSchema = true)
//@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun postCommentDao(): PostCommentDao
}
