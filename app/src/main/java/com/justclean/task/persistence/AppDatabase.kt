package com.justclean.task.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justclean.task.model.Post
import com.justclean.task.model.PostComment

@Database(entities = [Post::class, PostComment::class], version = 1, exportSchema = true)
//@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun PostDao(): PostDao
    abstract fun PostCommentDao(): PostCommentDao
}
