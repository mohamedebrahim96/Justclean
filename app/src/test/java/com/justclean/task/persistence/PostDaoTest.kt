/**
 * Created by @mohamedebrahim96 on 12/5/20.
 */
package com.justclean.task.persistence

import com.justclean.task.utils.MockUtil.mockPost
import com.justclean.task.utils.MockUtil.mockPostList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.hamcrest.core.Is.`is`



@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PostDaoTest : LocalDatabase() {

    private lateinit var postDao: PostDao

    @Before
    fun init() {
        postDao = db.postDao()
    }

    @Test
    fun insertAndLoadPostListTest() = runBlocking {
        val mockDataList = mockPostList()
        postDao.insertPostList(mockDataList)

        val loadFromDB = postDao.getAllPostList()
        MatcherAssert.assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = mockPost()
        MatcherAssert.assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }
}
