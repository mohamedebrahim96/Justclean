/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.repository

import androidx.annotation.WorkerThread
import com.justclean.task.network.PostClient
import com.justclean.task.persistence.PostDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

class MainRepository /*@Inject*/ constructor(
    private val postClient: PostClient,
    private val pokemonDao: PostDao
) : Repository {

    @WorkerThread
    suspend fun fetchPokemonList(
        page: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        var pokemons = pokemonDao.getPokemonList(page)
        if (pokemons.isEmpty()) {
            val response = postClient.fetchPokemonList(page = page)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    pokemons = response.results
                    pokemons.forEach { pokemon -> pokemon.page = page }
                    pokemonDao.insertPokemonList(pokemons)
                    emit(pokemonDao.getAllPokemonList(page))
                    onSuccess()
                }
            }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
                .onError {
                    onError(message())
                }
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
                .onException {
                    onError(message())
                }
        } else {
            emit(pokemonDao.getAllPokemonList(page))
            onSuccess()
        }
    }.flowOn(Dispatchers.IO)
}
