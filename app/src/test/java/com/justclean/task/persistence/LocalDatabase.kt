package com.justclean.task.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by @mohamedebrahim96 on 12/5/20.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
abstract class LocalDatabase {
    lateinit var db: AppDatabase

    @Before
    fun initDB() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}
