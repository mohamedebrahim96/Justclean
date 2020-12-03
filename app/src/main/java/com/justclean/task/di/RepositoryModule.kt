/**
 * Created by @mohamedebrahim96 on 03,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.di

import com.justclean.task.network.PostClient
import com.justclean.task.persistence.PostCommentDao
import com.justclean.task.persistence.PostDao
import com.justclean.task.repository.DetailRepository
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
        pokedexClient: PostClient,
        pokemonDao: PostDao
    ): MainRepository {
        return MainRepository(pokedexClient, pokemonDao)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideDetailRepository(
        pokedexClient: PostClient,
        postCommentDao: PostCommentDao
    ): DetailRepository {
        return DetailRepository(pokedexClient, postCommentDao)
    }
}