/**
 * Created by @mohamedebrahim96 on 03,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.di

import com.justclean.task.network.PostClient
import com.justclean.task.persistence.PostCommentDao
import com.justclean.task.persistence.PostDao
import com.justclean.task.repository.CommentRepository
import com.justclean.task.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        postClient: PostClient,
        postDao: PostDao
    ): MainRepository {
        return MainRepository(postClient, postDao)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideCommentRepository(
        postClient: PostClient,
        postCommentDao: PostCommentDao
    ): CommentRepository {
        return CommentRepository(postClient, postCommentDao)
    }
}