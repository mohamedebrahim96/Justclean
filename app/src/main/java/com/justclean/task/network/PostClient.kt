/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.network



class PostClient @Inject constructor(
        private val postService: PostService
    ) {

        suspend fun fetchPokemonList(page: Int) = postService.fetchPostList()

        suspend fun fetchPokemonInfo(id: Int) = postService.fetchPostComment(id)

        companion object {
            private const val PAGING_SIZE = 20
        }
    }
